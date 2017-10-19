package com.wally.hiread.service.user;

import com.wally.hiread.dao.product.PractiseMapper;
import com.wally.hiread.dao.product.ProductMapper;
import com.wally.hiread.dao.product.UnitMapper;
import com.wally.hiread.dao.product.UserProductMapper;
import com.wally.hiread.model.product.Product;
import com.wally.hiread.model.product.Unit;
import com.wally.hiread.model.product.UserProduct;
import com.wally.hiread.model.product.UserProductExample;
import com.wally.hiread.model.sys.Page;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.service.product.LearningProgressService;
import com.wally.hiread.service.product.PractiseOptService;
import com.wally.hiread.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UserProductService {
    @Autowired
    private UnitMapper unitMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PractiseMapper practiseMapper;
    @Autowired
    private PractiseOptService practiseOptService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserProductMapper userProductMapper;
    @Autowired
    private LearningProgressService lpService;

    public SR<List<UserProduct>> myUserProducts(String userId, int currentPage) {
        SR<List<UserProduct>> sr=new SR<List<UserProduct>>();
        UserProductExample e = new UserProductExample();
        UserProductExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        String orderBy="dateCreated desc";
        Page page=null;
        if(currentPage>0){
            int count = userProductMapper.countByExample(e);
            page=new Page(count,currentPage,6,5);
            orderBy+=page.getlimitSql();
        }
        e.setOrderByClause(orderBy);
        List<UserProduct> list = userProductMapper.selectByExample(e);
        List<UserProduct> result=new ArrayList<UserProduct>();
        for (UserProduct up : list) {
            result.add(this.myUserProduct(userId,up.getId(),false));
        }
        sr.setEntity(result);
        if(currentPage>0){
            sr.setPage(page);
        }
        sr.setStatus(SR.SUCCESS);
        return sr;
    }

    public UserProduct myUserProduct(String userId, String id,boolean verbose) {
        UserProductExample e = new UserProductExample();
        UserProductExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andIdEqualTo(id);
        List<UserProduct> list = userProductMapper.selectByExample(e);
        if (list != null && list.size() == 1) {
            UserProduct up = list.get(0);
            String status=up.getStatus();
            String unitId=up.getUnitId();
            String productId = up.getProductId();
            Product prod = productService.load(productId, true);
            up.setProduct(prod);
            List<Unit> units = prod.getUnits();
            int unitIdForUpdateIndex = unitIdForUpdateIndex(up);
            if(verbose){
                //设置用户课程单元解锁情况
                if(units!=null&&units.size()>0){
                    for(int i=0;i<units.size();i++){
                        Unit unit=units.get(i);
                        if(unitIdForUpdateIndex==-1){
                            if(!StringUtils.isEmpty(unitId)){
                                setUnitLockAll(unit,false);
                            }else{
                                setUnitLockAll(unit,true);
                            }
                        }else{
                            if(i<unitIdForUpdateIndex){
                                setUnitLockAll(unit,false);
                            }else if(i==unitIdForUpdateIndex){
                                unit.setUnitLocked(false);
                                unit.setPreviewLocked(getStatusLockForUnit(up,UserProduct.STATUS_PREVIEW));
                                unit.setPreviewHWLocked(getStatusLockForUnit(up,UserProduct.STATUS_PREVIEW_HW));
                                unit.setVideoLocked(getStatusLockForUnit(up,UserProduct.STATUS_VIDEO));
                                unit.setReviewHWLocked(getStatusLockForUnit(up,UserProduct.STATUS_REVIEW_HW));
                            }else{
                                setUnitLockAll(unit,true);
                            }
                        }
                    }
                }
                //设置课前、课后测试解锁情况
                setPrePostTestLock(up);
                //设置课程学习时间
                long totalTime = lpService.productLearningTime(userId, productId);
                long totalTimeHour = totalTime / 60;
                long totalTimeMin=totalTime%60;
                up.setTotalTime(totalTime);
                up.setTotalTimeHour(totalTimeHour);
                up.setTotalTimeMin(totalTimeMin);

                //设置正在学习
                String statusForUpdate = this.statusForUpdate(up);
                String unitIdForUpdate=unitIdForUpdate(up);
                up.setStatusForUpdate(statusForUpdate);
                up.setUnitIdForUpdate(unitIdForUpdate);
                up.setUnitIdForUpdateIndex(unitIdForUpdateIndex);
            }

            //设置用户课程单元完成百分比
            if(units!=null&&units.size()>0){
                if(unitIdForUpdateIndex==-1){
                    if(!StringUtils.isEmpty(unitId)){
                        up.setPercent("100");
                    }else{
                        up.setPercent("0");
                    }
                }else{
                    List<Unit> done=new ArrayList<Unit>();
                    List<Unit> undone=new ArrayList<Unit>();
                    for(int i=0;i<units.size();i++){
                        Unit u=units.get(i);
                        if(i<unitIdForUpdateIndex){
                            done.add(u);
                        }else{
                            undone.add(u);
                        }
                    }
                    double t=new BigDecimal(done.size()/(double)units.size()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    String percent=new BigDecimal(t*100).setScale(0,BigDecimal.ROUND_HALF_UP).toString();
                    up.setPercent(percent);
                }
            }else{
                up.setPercent("0");
            }
            return up;
        }
        return null;
    }



    public void update(UserProduct up) {
        Date now = new Date();
        up.setDatemodified(now);
        userProductMapper.updateByPrimaryKey(up);
    }

    //设置课前、课后测试解锁情况
    private void setPrePostTestLock(UserProduct up){
        String status=up.getStatus();
        if(StringUtils.isEmpty(status)){
            up.setPreTestFreeTalkLocked(true);
        }else{
            up.setPreTestFreeTalkLocked(false);
        }
        if(this.isLastUnit(up)){
            if(!StringUtils.isEmpty(status)&&status.equals(UserProduct.STATUS_REVIEW_HW)){
                up.setPostTestLocked(false);
                up.setPostTestPractiseLocked(false);
                up.setPostTestFreeTalkLocked(true);
            }else if(!StringUtils.isEmpty(status)&&
                    (status.equals(UserProduct.STATUS_POSTTEST_PRACTISE)||status.equals(UserProduct.STATUS_POSTTEST_FREE_TALK))){
                up.setPostTestLocked(false);
                up.setPostTestPractiseLocked(false);
                up.setPostTestFreeTalkLocked(false);
            }else{
                up.setPostTestLocked(true);
                up.setPostTestPractiseLocked(true);
                up.setPostTestFreeTalkLocked(true);
            }
        }else{
            up.setPostTestLocked(true);
            up.setPostTestPractiseLocked(true);
            up.setPostTestFreeTalkLocked(true);
        }
    }
    //设置单元解锁情况
    private void setUnitLockAll(Unit unit,boolean lock){
        unit.setUnitLocked(lock);
        unit.setPreviewLocked(lock);
        unit.setPreviewHWLocked(lock);
        unit.setVideoLocked(lock);
        unit.setReviewHWLocked(lock);
    }
    //获取单元解锁情况
    public boolean getStatusLockForUnit(UserProduct up,String s){
        String status = up.getStatus();
        boolean lock=true;
        if (status.equals(UserProduct.STATUS_PRETEST_FREE_TALK)) {
            if(s.equals(UserProduct.STATUS_PREVIEW)){
                lock=false;
            }else{
                lock=true;
            }
        } else if(status.equals(UserProduct.STATUS_PREVIEW)
                &&(s.equals(UserProduct.STATUS_PREVIEW)||s.equals(UserProduct.STATUS_PREVIEW_HW))){
            lock=false;
        }else if(status.equals(UserProduct.STATUS_PREVIEW_HW)
                &&(s.equals(UserProduct.STATUS_PREVIEW)||s.equals(UserProduct.STATUS_PREVIEW_HW)||s.equals(UserProduct.STATUS_VIDEO))){
            lock=false;
        }else if(status.equals(UserProduct.STATUS_VIDEO)){
            lock=false;
        }else if(status.equals(UserProduct.STATUS_REVIEW_HW)&&s.equals(UserProduct.STATUS_PREVIEW)){
            lock=false;
        }
        return lock;
    }
    /*
    合法的课程status更新
    */
    public String statusForUpdate(UserProduct up) {
        String status = up.getStatus();
        if(StringUtils.isEmpty(status)){
            return UserProduct.STATUS_PRETEST_PRACTISE;
        }
        if(status.equals(UserProduct.STATUS_PRETEST_PRACTISE)){
            return UserProduct.STATUS_PRETEST_FREE_TALK;
        }
        if(status.equals(UserProduct.STATUS_PRETEST_FREE_TALK)){
            return UserProduct.STATUS_PREVIEW;
        }
        if(status.equals(UserProduct.STATUS_PREVIEW)){
            return UserProduct.STATUS_PREVIEW_HW;
        }
        if(status.equals(UserProduct.STATUS_PREVIEW_HW)){
            return UserProduct.STATUS_VIDEO;
        }
        if(status.equals(UserProduct.STATUS_VIDEO)){
            return UserProduct.STATUS_REVIEW_HW;
        }
        if(status.equals(UserProduct.STATUS_REVIEW_HW)){
            boolean isLastUnit =isLastUnit(up);
            if(isLastUnit){
                return UserProduct.STATUS_POSTTEST_PRACTISE;
            }else{
                return UserProduct.STATUS_PREVIEW;
            }
        }
        if(status.equals(UserProduct.STATUS_POSTTEST_PRACTISE)){
            return UserProduct.STATUS_POSTTEST_FREE_TALK;
        }
        return null;
    }
    /*
    合法的课程unitId更新
     */
    public String unitIdForUpdate(UserProduct up) {
        String status = up.getStatus();
        if (StringUtils.isEmpty(status)
                ||status.equals(UserProduct.STATUS_PRETEST_PRACTISE)
                ||status.equals(UserProduct.STATUS_POSTTEST_PRACTISE)
                ||status.equals(UserProduct.STATUS_POSTTEST_FREE_TALK)) {
            return null;
        }
        List<Unit> units = up.getProduct().getUnits();
        String unitId = up.getUnitId();
        if (StringUtils.isEmpty(unitId)) {
            if (units == null || units.size() == 0) {
                return null;
            }
            return units.get(0).getId();
        }
        int unitIndex = -1;
        for (int i = 0; i < units.size(); i++) {
            Unit unit = units.get(i);
            if (unit.getId().equals(unitId)) {
                unitIndex = i;
                break;
            }
        }
        if (unitIndex == -1) {
            return null;
        }
        if (UserProduct.STATUS_REVIEW_HW.equals(status)) {
            if (unitIndex >= units.size() - 1) {
                return null;
            }
            return units.get(unitIndex + 1).getId();
        } else {
            return unitId;
        }
    }
    /*
    合法的课程unitId更新在units中的index
     */
    public int unitIdForUpdateIndex(UserProduct up){
        String unitIdForUpdate = this.unitIdForUpdate(up);
        if(unitIdForUpdate==null){
            return -1;
        }
        List<Unit> units=up.getProduct().getUnits();
        if(unitIdForUpdate==null||units==null||units.size()==0){
            return -1;
        }
        for(int i=0;i<units.size();i++){
            Unit unit = units.get(i);
            if(unit.getId().equals(unitIdForUpdate)){
                return i;
            }
        }
        return -1;
    }

    //课程是否已上到最后一个单元
    private boolean isLastUnit(UserProduct up){
        String unitId = up.getUnitId();
        String status=up.getStatus();
        if(StringUtils.isEmpty(unitId)||StringUtils.isEmpty(status)){
            return false;
        }
        Product product = up.getProduct();
        if(product==null){
            return false;
        }
        List<Unit> units = product.getUnits();
        if(units==null||units.size()==0){
            return false;
        }
        String lastUnitId=units.get(units.size()-1).getId();
        if(!unitId.equals(lastUnitId)){
            return false;
        }
        return true;
    }

}

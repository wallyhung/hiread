package com.wally.hiread.model.sys;

public class Page {
    private int maxEntityPerPage;
    private int maxPagePerRow;

    private int entityCount;
    private int currentPage;

    private int pageCount;
    private int currentPageRow;
    private int pageRowCount;


    public int getPageRowCount() {
        return pageRowCount;
    }
    public int getEntityCount() {
        return entityCount;
    }
    public int getMaxEntityPerPage() {
        return maxEntityPerPage;
    }
    public int getMaxPagePerRow() {
        return maxPagePerRow;
    }

    public int getCurrentPage() {
        return currentPage;
    }
    public int getPageCount() {
        return pageCount;
    }
    public int getCurrentPageRow() {
        return currentPageRow;
    }
    private void refreshPage(){
        this.pageCount=this.entityCount/this.maxEntityPerPage+(this.entityCount%this.maxEntityPerPage==0?0:1);
        this.currentPageRow=this.currentPage/this.maxPagePerRow+(this.currentPage%this.maxPagePerRow==0?0:1);
        this.pageRowCount=this.pageCount/this.maxPagePerRow+(this.pageCount%this.maxPagePerRow==0?0:1);
    }

    /**
     * 换页
     */
    public void setCurrentPage(int currentPage){
        this.currentPage=currentPage;
        refreshPage();
    }
    /**设置实体数量
     * @param count
     */
    public void setEntityCount(int count){
        this.entityCount=count;
        refreshPage();
    }
    public Page(){
        this.maxEntityPerPage=15;
        this.maxPagePerRow=10;
        this.currentPage=1;
        this.entityCount=0;
        refreshPage();
    }
    public Page(int entityCount,int currentPage){
        this.maxEntityPerPage=15;
        this.maxPagePerRow=10;
        this.currentPage=currentPage;
        this.entityCount=entityCount;
        refreshPage();
    }
    public Page(int entityCount,int currentPage,int maxEntityPerPage,int maxPagePerRow){
        this.maxEntityPerPage=maxEntityPerPage;
        this.maxPagePerRow=maxPagePerRow;
        this.currentPage=currentPage;
        this.entityCount=entityCount;
        refreshPage();
    }

    /**获取sql用分页limit语句
     * @return
     */
    public String getlimitSql(){
        return " limit "+(currentPage-1)*maxEntityPerPage+","+maxEntityPerPage;
    }
}

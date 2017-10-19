var birdGame = {
    TOTLE: 16,//存放卡片的表格数
    totle: 8,//总的卡片数
    imgs: [],//用来保存所有图片地址
    randomImg: [],//随机生成的牌
    //gameTime:120,//游戏的时间
    seeTime: 5,//观看卡片的时间
    onloadImg: 0,//载入的图片数量
    timer: null,//保存游戏进行中定时器序号
    first: -1,//第一次翻开的图片
    lockCard: 0,//是否锁定翻开的牌
    peidui: 0,//配对成功次数
    timeCount: 100,//游戏倒计时
    Count: 100,//计数器配合倒计时用
    state: 5,//保存游戏状态
    GAMEOVER: 6,//表示游戏结束状态
    RUNNING: 1,//游戏运行中
    PAUSE: 2,//游戏暂停
    CONTUNUE: 3,//继续游戏
    SUCC: 4,//游戏成功
    SCORE: 0,//游戏得分
    start: function () {//用来启动游戏
        //重置参数
        var ulclick = document.getElementById("bubbling");
        //利用冒泡为游戏按钮添加单击事件分别控制开始和暂停
        ulclick.addEventListener("click", function (e) {
            var target = e.target;
            if ("开始游戏" == target.innerHTML) {
                if (this.state != this.PAUSE) {
                    if (this.state == this.GAMEOVER || this.state == this.SUCC || this.state == this.RUNNING) {
                        clearInterval(this.timer);
                        this.timer = null;
                        this.SCORE = 0;
                        this.timeCount = this.Count;
                        this.score();
                        document.getElementById("timeCal")
                            .innerHTML = "游戏时间:" + this.timeCount + "s";
                        document.getElementById("mask")
                            .className = "mask_every nosee";
                        document.getElementById("success")
                            .className = "mask_every nosee";
                        $('game-img').hide();
                    }
                    //this.state=this.RUNNING;
                    var birdImg = document.getElementsByClassName("birdImg")[0];
                    birdImg.innerHTML = "";//清空页面
                    this.first = -1;
                    this.lockCard = 0;
                    this.peidui = 0;
                    this.setHtml();
                    var time = this.seeTime;
                    var timer1 = setInterval(function () {//////
                        if (0 == time) {//启动游戏
                            this.timer = setInterval(this.timeCal.bind(this), 1000);//游戏倒计时
                            this.rotedAll();
                            this.click();
                            clearInterval(timer1);
                            this.state = this.RUNNING;/////////////
                        }
                        if (this.seeTime == time) {
                            this.rotedAll();
                        }
                        time--;//观察图片时间
                    }.bind(this), 1000);
                }
            } else if (("暂停游戏" == target.innerHTML)) {
                if (this.state == this.RUNNING) {
                    console.log(this.state);
                    this.pause();
                }
            } else if ("继续游戏" == target.innerHTML) {
                if (this.state == this.PAUSE) {
                    this.contin();
                }
            } else if ("退出游戏" == target.innerHTML) {
                this.close();
            }
        }.bind(this));
    },
    timeCal: function () {//游戏倒计时游戏结束部分
        var li = document.getElementById("timeCal");//找到页面时间部分
        li.innerHTML = "游戏时间:" + this.timeCount + "s";
        this.timeCount--;
        if (0 == this.timeCount) {
            document.getElementById("mask").className = "mask_every block";
            li.innerHTML = "游戏结束";
            clearInterval(this.timer);
            this.timer = null;
            this.state = this.GAMEOVER;
            //this.timeCount=this.Count;
        }
    },
    pause: function () {//暂停游戏
        this.state = this.PAUSE;
        clearInterval(this.timer);
        this.timer = null;
        //$("#pause").css("display","none");
        //document.getElementById("pause").style.display="block";
        document.getElementById("pause").className = "mask_every block";
    },
    contin: function () {//继续游戏
        this.state = this.RUNNING;
        document.getElementById("pause").className = "mask_every";
        this.timer = setInterval(this.timeCal.bind(this), 1000)
    },
    close: function () {//退出游戏
        window.close();
    },
    score: function () {//得分部分
        var ul = document.getElementById("bubbling");
        ul.children[0].innerHTML = "Score:" + this.SCORE;
    },
    getImg: function () {//获得所有图片地址
        for (var i = 0; i < this.totle; i++) {
            //把图片路径压入imgs数组---成对生成
            this.imgs.push({"cardinfi": "img/" + i + "_a.png", "turn": 0},
                {"cardinfi": "img/" + i + "_a.png", "turn": 0});
        }
        var num = this.imgs.length;
        for (var r = 0, randomImg = []; r < num; r++) {//生成随机位置的牌
            var n = Math.floor(Math.random() * this.imgs.length);
            //随机生成一个0-imgs.length之间的数截取n截取对应位置的元素
            //通过pop转化为值压入imgs
            randomImg.push(this.imgs.splice(n, 1).pop());
        }
        return randomImg;
    },
    setHtml: function () {//添加元素到页面
        this.randomImg = this.getImg();
        var frag = document.createDocumentFragment();
        for (var i = 0, temImg = [], backImg = []; i < this.TOTLE; i++) {
            //创建div元素用来保存img
            var div = document.createElement("div")
            //设置生成的div的class
            div.className = "imgContainer";
            var div1 = document.createElement("div");
            div1.id = "card" + i;
            div1.className = "card viewport-flip";
            div.appendChild(div1);
            var div2 = document.createElement("div");
            div2.className = "list flip out";//反面
            div1.appendChild(div2);
            var div3 = document.createElement("div");
            div3.className = "list flip";
            div1.appendChild(div3);
            //创建生成图片img标签保存在temImg中
            temImg[i] = new Image();
            //设置生成的当前img的src属性为randomImg当前元素的cardinfi属性
            temImg[i].src = this.randomImg[i]["cardinfi"];
            //创建反过去后的图片img
            backImg[i] = new Image();
            //设置生成的backImg的src属性为images/backImg.png;
            backImg[i].src = "img/backImg.png";
            //添加生成的img到生成的div
            div2.appendChild(temImg[i]);
            div3.appendChild(backImg[i]);
            //添加到文档片段
            frag.appendChild(div);
        }
        //添加
        document.getElementById("birdImg").appendChild(frag);
    },
    rotedAll: function () {//把图片全翻过去
        for (var i = 0; i < this.randomImg.length; i++) {
            this.roted(i)
        }
    },
    roted: function (idx) {//图片翻转的动画
        var div = document.getElementById("card" + idx);
        var divs = div.querySelectorAll("div");
        var cardfont, cardback;
        if (divs[0].className.indexOf("out") !== -1) {
            cardfont = divs[0];
            cardback = divs[1];
        } else {
            cardfont = divs[1];
            cardback = divs[0];
        }
        setTimeout(function () {//翻转动画定时器
            cardback.className = "list flip out";
            cardfont.className = "list flip in";
        }, 500);
        //debugger;
    },
    click: function () {//添加单机事件
        for (var i = 0; i < this.TOTLE; i++) {
            var div = document.getElementById("card" + i);
            div.addEventListener("click", turn.bind(this));
        }
        function turn(e) {//点击事件的函数点击后翻牌
            var tar = e.target;
            var div = tar.parentNode.parentNode;
            var n = parseInt(div.id.replace("card", ""));
            if (this.lockCard == 1) {
                return;
            }
            else if (this.randomImg[n]["turn"] == 0) {//没有翻开
                //console.log(this.first);
                if (this.first == -1) {//第一次翻开的图片
                    this.roted(n);
                    this.first = n;
                    this.randomImg[n]["turn"] = 1;
                    //console.log(this.first);
                }
                else {//第二次翻开
                    this.roted(n);
                    this.randomImg[n]["turn"] = 1;
                    this.check(n);//检查是否翻开的一样
                }
            }
        }
    },
    check: function (n) {// 检查是否翻牌成功
        this.lockCard = 1;
        //判断第一次点击和第二次点击图片路径是否相同
        if ((this.randomImg[this.first]["cardinfi"]) == (this.randomImg[n]["cardinfi"])) {
            this.SCORE += 10;
            this.score();
            this.peidui++;
            if (this.peidui == this.totle) {
                clearInterval(this.timer);
                this.timer = null;
                this.state = this.SUCC;
                document.getElementById("success")
                    .className = "mask_every block";
                //this.timeCount=this.Count;
            }
            this.first = -1;
            this.lockCard = 0;
        }
        else {//配对失败,将翻开的牌翻转
            var et = setTimeout(function () {
                this.roted(n);
                this.roted(this.first);
                this.randomImg[n]["turn"] = 0;
                this.randomImg[this.first]["turn"] = 0;
                this.first = -1;
                this.lockCard = 0;
            }.bind(this), 1000);
        }
    }
};
birdGame.start();

$(function () {
    $('.mask_every,.game-btn').click(function () {
        $('.mask_every').hide();
    })
});
hi.alert=function(msg){
    layer.open({
        title: '温馨提示',
        content: msg
    });
};
hi.bus=new Vue();
hi.ax = axios.create({
    baseURL: hi.root
});
hi.ax.interceptors.response.use(function (response) {
    // Do something with response data
    if(!response.data){
        console.log(response);
        hi.alert("Sorry, The system is being maintained！");
        return Promise.reject(response);
    }

    return response;
}, function (error) {
    // Do something with response error
    console.log(error);
    if(error.status=='403'){
        hi.alert("对不起，您没有权限！");
        return Promise.reject(error);
    }
    hi.alert("系统正在维护，为您带来的不便敬请谅解！");
    return Promise.reject(error);
});
hi.round=function (value,decimals) {
    return Number(Math.round(value+'e'+decimals)+'e-'+decimals);
}
// hi.auth={isLogin:false,user:null};
// hi.ax.get('/user/login/refreshLoginUser',{}).then(function (r) {
//     if(r.data.status=='fail'){
//         hi.auth.isLogin=false;
//         hi.auth.user=null;
//     }else if(r.data.status=='success'){
//         hi.auth.isLogin=true;
//         hi.auth.user=r.data.entity;
//     }
//
// }).catch(function (error) {
//     console.log(error);
// })
/* hi.ax2 = axios.create({
 baseURL: hi.contextRoot,
 headers: {'Content-Type': 'application/x-www-form-urlencoded'}
 }); */
hi.queryString = function () {
    // This function is anonymous, is executed immediately and
    // the return value is assigned to QueryString!
    var query_string = {};
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        // If first entry with this name
        if (typeof query_string[pair[0]] === "undefined") {
            query_string[pair[0]] = decodeURIComponent(pair[1]);
            // If second entry with this name
        } else if (typeof query_string[pair[0]] === "string") {
            var arr = [ query_string[pair[0]],decodeURIComponent(pair[1]) ];
            query_string[pair[0]] = arr;
            // If third or later entry with this name
        } else {
            query_string[pair[0]].push(decodeURIComponent(pair[1]));
        }
    }
    return query_string;
}();

hi.mixin = {
    methods: {
        numeral: function (v) {
            return numeral(v);
        },
        empty:function(v){
            if(v===null){
                return true;
            }
            if(typeof v==='string'&&v.trim()===''){
                return true;
            }
            if(Array.isArray(v)&&v.length===0){
                return true;
            }
            if(typeof v==='object'){
                var t=true;
                for (var name in v){
                    t=false;
                }
                if(t){
                    return true;
                }
            }
            return false;
        },
        lineBreakText:function(v){
            var v=v.replace(/\n/g,'<br>');
            return v;
        }

    }
};
hi.drag=null;


/*
 * public.js begin----------------------------------------------->
 */

// See the Getting Started docs for more information:
// http://getbootstrap.com/getting-started/#support-ie10-width
//banner
(function () {
    'use strict';
    if (navigator.userAgent.match(/IEMobile\/10\.0/)) {
        var msViewportStyle = document.createElement('style')
        msViewportStyle.appendChild(
            document.createTextNode(
                '@-ms-viewport{width:auto!important}'
            )
        );
        document.querySelector('head').appendChild(msViewportStyle)
    }
})();
$(".nav-tabs1 a,.nav-tabs2 a").mousemove(function (e) {
    $(this).tab('show');
});
//隔行滚动新闻
function AutoScroll(obj) {
    $(obj).find("ul:first").animate({
        marginTop: "-25px"
    }, 500, function () {
        $(this).css({marginTop: "0px"}).find("li:first").appendTo(this);
    });
}
$(function () {
    setInterval('AutoScroll(".scrollDiv")', 3000);

    //user left menu
    //Initialize the page menu
    $(".learning-nav a").each(function (i, e) {
        if (window.location.href.indexOf($(e).attr("abbr")) >= 0) {
            $(e).addClass("selected");
        }
    });
    // //Initialize the selected menu
    // $(".navMenu dl").each(function () {
    //     if ($(this).hasClass('selected')) {
    //         $(this).find("dt a").css('color', '#333').find("i").removeClass("fa-chevron-down").addClass("fa-chevron-up");
    //         $(this).find("dd").slideDown(100);
    //     }
    // });
    // //Menu display and hide
    // $(".navMenu dt").click(function () {
    //     if ($(this).find("i").hasClass('fa-chevron-down')) {
    //         $(this).find("a").css('color', '#333').find("i").removeClass("fa-chevron-down").addClass("fa-chevron-up");
    //         $(this).parent().find('dd').slideDown(100);
    //     } else {
    //         $(this).find("a").css('color', '#333').find("i").removeClass("fa-chevron-up").addClass("fa-chevron-down");
    //         $(this).parent().find('dd').slideUp(100);
    //     }
    // });
    //选择
    $(".btn-choice").click(function () {
        $(this).addClass('btn-choice1').siblings().removeClass('btn-choice1');
    });

    //二维码
    $('.top-phone').hover(function () {
        $(this).addClass('top-phone-hover');
    }, function () {
        $(this).removeClass('top-phone-hover');
    });

});
(function ($, window, undefined) {
// outside the scope of the jQuery plugin to
// keep track of all dropdowns
    var $allDropdowns = $();
// if instantlyCloseOthers is true, then it will instantly
// shut other nav items when a new one is hovered over
    $.fn.dropdownHover = function (options) {
// the element we really care about
// is the dropdown-toggle's parent
        $allDropdowns = $allDropdowns.add(this.parent());
        return this.each(function () {
            var $this = $(this).parent(),
                defaults = {
                    delay: 500,
                    instantlyCloseOthers: true
                },
                data = {
                    delay: $(this).data('delay'),
                    instantlyCloseOthers: $(this).data('close-others')
                },
                options = $.extend(true, {}, defaults, options, data),
                timeout;
            $this.hover(function () {
                if (options.instantlyCloseOthers === true)
                    $allDropdowns.removeClass('open');
                window.clearTimeout(timeout);
                $(this).addClass('open');
            }, function () {
                timeout = window.setTimeout(function () {
                    $this.removeClass('open');
                }, options.delay);
            });
        });
    };
    $('[data-hover="dropdown"]').dropdownHover();
})(jQuery, this);

$(function () {
    $('.questionnaire-btn').click(function () {

    })

    $(".weixin_icon").mouseover(function () {
        $(".weixin_code").show();
    });
    $(".weixin_icon").mouseout(function () {
        $(".weixin_code").hide();
    });

});


$(function () {

    $(".btn-c1").click(function () {
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            shadeClose: false,
            area: ['auto', 'auto'],
            content: $('.popup-content1').html(),
            success: function () {
                layui.form().render()
            }
        })
    });

    $(".order-add-wp").click(function () {
        $(this).addClass('order-add-link').siblings().removeClass('order-add-link');
    });
    $(".order-link").click(function () {
        $(this).addClass('order-link-hover').siblings().removeClass('order-link-hover');
    });

    //置顶图标显示
    $('#top-back').hide();
    $(window).scroll(function () {
        if ($(this).scrollTop() > 350) {
            $("#top-back").fadeIn();
        }
        else {
            $("#top-back").fadeOut();
        }
    });


});
//置顶事件
function topBack() {
    $('body,html').animate({scrollTop: 0}, 300);
}


/*!
 * IE10 viewport hack for Surface/desktop Windows 8 bug
 * Copyright 2014 Twitter, Inc.
 * Licensed under the Creative Commons Attribution 3.0 Unported License. For
 * details, see http://creativecommons.org/licenses/by/3.0/.
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

});


$(function () {
    $(function () {

        $('.order-add-s').click(function () {
            layer.confirm('确定要删除吗？', {
                title: false,
                closeBtn: 0,
                btn: ['确定', '取消'] //按钮
            }, function () {
                layer.msg('删除成功！', {icon: 1});
            }, function () {
                layer.msg('取消成功！', {});
            });
        })
    });
    $(".order-add-g").click(function () {
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            shadeClose: false,
            area: ['auto', 'auto'],
            content: $('.popup-content')

        })
    });
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

    $(".layui-layer-close").click(function () {
        $('.popup-content').hide();
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


    $(function () {
        /*添加到购物车*/
        var carN = parseInt($("#badge").text());
        $(".add-car").each(function () {
            $(this).on('click', function (e) {
                if (!$(this).hasClass("disabled")) {
                    var offset = $("#badge").position(),
                        flyer = $('<i class="fa fa-shopping-cart" style="font-size: 30px; color: red;"></i>');
                    flyer.fly({
                        start: {
                            left: e.clientX,
                            top: e.clientY
                        },
                        end: {
                            left: $("#badge").offset().left,
                            top: 800,
                            width: 20,
                            height: 20
                        },
                        onEnd: function () {
                            $(".badge1").text(carN += 1);
                            flyer.fadeOut('slow');
                        }
                    });
                    $('.cndns-right-btn').removeClass('cndns-right-btn0');
                }
            });
        })
    })
});
//置顶事件
function topBack() {
    $('body,html').animate({scrollTop: 0}, 300);
}
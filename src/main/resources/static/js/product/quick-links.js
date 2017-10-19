
//ds.base
(function (global, document, undefined) {
    var
        rblock = /\{([^\}]*)\}/g,
        ds = global.ds = {
            noop: function () {
            },
            //Object
            mix: function (target, source, cover) {
                if (typeof source !== 'object') {
                    cover = source;
                    source = target;
                    target = this;
                }
                for (var k in source) {
                    if (cover || target[k] === undefined) {
                        target[k] = source[k];
                    }
                }
                return target;
            },
            //String
            mixStr: function (sStr) {
                var args = Array.prototype.slice.call(arguments, 1);
                return String(sStr).replace(rblock, function (a, i) {
                    return args[i] != null ? args[i] : a;
                });
            },
            trim: function (str) {
                return String(str).replace(/^\s*/, '').replace(/\s*$/, '');
            },
            //Number
            getRndNum: function (max, min) {
                min = isFinite(min) ? min : 0;
                return Math.random() * ((isFinite(max) ? max : 1000) - min) + min;
            },
            //BOM
            scrollTo: (function () {
                var
                    duration = 480,
                    view = $(global),
                    setTop = function (top) {
                        global.scrollTo(0, top);
                    },
                    fxEase = function (t) {
                        return (t *= 2) < 1 ? .5 * t * t : .5 * (1 - (--t) * (t - 2));
                    };
                return function (top, callback) {
                    top = Math.max(0, ~~top);
                    var
                        tMark = new Date(),
                        currTop = view.scrollTop(),
                        height = top - currTop,
                        fx = function () {
                            var tMap = new Date() - tMark;
                            if (tMap >= duration) {
                                setTop(top);
                                return (callback || ds.noop).call(ds, top);
                            }
                            setTop(currTop + height * fxEase(tMap / duration));
                            setTimeout(fx, 16);
                        };
                    fx();
                };
            })(),
            //DOM
            loadScriptCache: {},
            loadScript: function (url, callback, args) {
                var cache = this.loadScriptCache[url];
                if (!cache) {
                    cache = this.loadScriptCache[url] = {
                        callbacks: [],
                        url: url
                    };

                    var
                        firstScript = document.getElementsByTagName('script')[0],
                        script = document.createElement('script');
                    if (typeof args === 'object') {
                        for (var k in args) {
                            script[k] = args[k];
                        }
                    }
                    script.src = url;
                    script.onload = script.onreadystatechange =
                        script.onerror = function () {
                            if (/undefined|loaded|complete/.test(this.readyState)) {
                                script = script.onreadystatechange =
                                    script.onload = script.onerror = null;
                                cache.loaded = true;

                                for (var i = 0, len = cache.callbacks.length; i < len; i++) {
                                    cache.callbacks[i].call(null, url);
                                }
                                cache.callbacks = [];
                            }
                        };
                    firstScript.parentNode.insertBefore(script, firstScript);
                }

                if (!cache.loaded) {
                    if (typeof callback === 'function') {
                        cache.callbacks.push(callback);
                    }
                }
                else {
                    (callback || ds.noop).call(null, url);
                }
                return this;
            },
            requestAnimationFrame: (function () {
                var handler = global.requestAnimationFrame || global.webkitRequestAnimationFrame ||
                    global.mozRequestAnimationFrame || global.msRequestAnimationFrame ||
                    global.oRequestAnimationFrame || function (callback) {
                        return global.setTimeout(callback, 1000 / 60);
                    };
                return function (callback) {
                    return handler(callback);
                };
            })(),
            animate: (function () {
                var
                    easeOut = function (pos) {
                        return (Math.pow((pos - 1), 3) + 1);
                    };
                getCurrCSS = global.getComputedStyle ? function (elem, name) {
                    return global.getComputedStyle(elem, null)[name];
                } : function (elem, name) {
                    return elem.currentStyle[name];
                };
                return function (elem, name, to, duration, callback, easing) {
                    var
                        from = parseFloat(getCurrCSS(elem, name)) || 0,
                        style = elem.style,
                        tMark = new Date(),
                        size = 0;

                    function fx() {
                        var elapsed = +new Date() - tMark;
                        if (elapsed >= duration) {
                            style[name] = to + 'px';
                            return (callback || ds.noop).call(elem);
                        }
                        style[name] = (from + size * easing(elapsed / duration)) + 'px';
                        ds.requestAnimationFrame(fx);
                    };
                    to = parseFloat(to) || 0;
                    duration = ~~duration || 200;
                    easing = easing || easeOut;
                    size = to - from;
                    fx();
                    return this;
                };
            })(),
            //Cookies
            getCookie: function (name) {
                var ret = new RegExp('(?:^|[^;])' + name + '=([^;]+)').exec(document.cookie);
                return ret ? decodeURIComponent(ret[1]) : '';
            },
            setCookie: function (name, value, expir) {
                var cookie = name + '=' + encodeURIComponent(value);
                if (expir !== void 0) {
                    var now = new Date();
                    now.setDate(now.getDate() + ~~expir);
                    cookie += '; expires=' + now.toGMTString();
                }
                document.cookie = cookie;
            },
            //Hacker
            transitionSupport: (function () {
                var
                    name = '',
                    prefixs = ['', 'webkit', 'moz', 'ms', 'o'],
                    docStyle = (document.documentElement || document.body).style;
                for (var i = 0, len = prefixs.length; i < len; i++) {
                    name = prefixs[i] + (prefixs[i] !== '' ? 'Transition' : 'transition');
                    if (name in docStyle) {
                        return {
                            propName: name,
                            prefix: prefixs[i]
                        };
                    }
                }
                return null;
            })(),
            isIE6: !-[1,] && !global.XMLHttpRequest
        };

})(this, document);


;(function (global) {
    var ds = global.ds || (global.ds = {});
    var rarg1 = /\$1/g, rgquote = /\\"/g, rbr = /([\r\n])/g, rchars = /(["\\])/g, rdbgstrich = /\\\\/g, rfuns = /<%\s*(\w+|.)([\s\S]*?)\s*%>/g, rbrhash = {'10': 'n', '13': 'r'}, helpers = {'=': {render: '__.push($1);'}};
    ds.tmpl = function (tmpl, data) {
        var render = new Function('_data', 'var __=[];__.data=_data;' + 'with(_data){__.push("' + tmpl.replace(rchars, '\\$1').replace(rfuns, function (a, key, body) {
                body = body.replace(rbr, ';').replace(rgquote, '"').replace(rdbgstrich, '\\');
                var helper = helpers[key], tmp = !helper ? key + body : typeof helper.render === 'function' ? helper.render.call(ds, body, data) : helper.render.replace(rarg1, body);
                return '");' + tmp + '__.push("';
            }).replace(rbr, function (a, b) {
                return '\\' + (rbrhash[b.charCodeAt(0)] || b);
            }) + '");}return __.join("");');
        return data ? render.call(data, data) : render;
    };
    ds.tmpl.helper = helpers;
})(this);

function renderQuickLinks(){
    //创建DOM
    var
        quickHTML = document.querySelector("div.quick_link_mian"),
        quickShell = $(document.createElement('div')).html(quickHTML).addClass('quick_links_wrap'),
        quickLinks = quickShell.find('.quick_links');
    quickPanel = quickLinks.next();
    quickShell.appendTo('.mui-mbar-tabs');

    //具体数据操作
    var
        quickPopXHR,
        loadingTmpl = '',
        popTmpl = '<a href="javascript:;" class="ibar_closebtn" title="关闭"><i class="fa fa-chevron-right"></i></a><div class="pop_panel"><%=content%></div><div class="arrow"><i></i></div><div class="fix_bg"></div>',
        historyListTmpl = '',
        newMsgTmpl = '',
        quickPop = quickShell.find('#quick_links_pop'),
        quickDataFns = {
            section_list: {
                title: '目录',
                content: $('.content-section').html(),
                init: $.noop
            },
            gami_list: {
                title: 'Gami',
                content: $('.content-gami').html(),
                init: $.noop
            },
            hint_list: {
                title: '提示',
                content: $(".content-hint").html(),
                init: $.noop
            }
        };

    //showQuickPop
    var
        prevPopType,
        prevTrigger,
        doc = $(document),
        popDisplayed = false,
        hideQuickPop = function () {
            if (prevTrigger) {
                prevTrigger.removeClass('current');
                $('.class-left').css("width","calc(100% - 60px)");
            }
            popDisplayed = false;
            prevPopType = '';
            quickPop.hide();
            quickPop.animate({left: 280, queue: true});
        },
        showQuickPop = function (type) {
            if (quickPopXHR && quickPopXHR.abort) {
                quickPopXHR.abort();
            }
            if (type !== prevPopType) {
                var fn = quickDataFns[type];
                quickPop.html(ds.tmpl(popTmpl, fn));
                fn.init.call(this, fn);
            }
            doc.unbind('click.quick_links').one('click.quick_links', hideQuickPop);

            quickPop[0].className = 'quick_links_pop quick_' + type;
            popDisplayed = true;
            prevPopType = type;
            quickPop.show();
            quickPop.animate({left: 0, queue: true});
            $('.class-left').css("width","calc(100% - 60px)");
        };
    quickShell.bind('click.quick_links', function (e) {
        e.stopPropagation();
    });
    quickPop.delegate('a.ibar_closebtn', 'click', function () {
        quickPop.hide();
        quickPop.animate({left: 280, queue: true});
        $('.class-left').css("width","calc(100% - 60px)");
        if (prevTrigger) {
            prevTrigger.removeClass('current');
        }
    });

    //通用事件处理
    var
        view = $(window),
        quickLinkCollapsed = !!ds.getCookie('ql_collapse'),
        getHandlerType = function (className) {
            return className.replace(/current/g, '').replace(/\s+/, '');
        },
        showPopFn = function () {
            var type = getHandlerType(this.className);
            if (popDisplayed && type === prevPopType) {
                return hideQuickPop();
            }
            showQuickPop(this.className);
            if (prevTrigger) {
                prevTrigger.removeClass('current');
                $('.class-left').css("width","calc(100% - 60px)");
            }
            prevTrigger = $(this).addClass('current');
            $('.class-left').css("width","calc(100% - 420px)");
        },
        quickHandlers = {
            section_list: showPopFn,
            gami_list: showPopFn,
            hint_list: showPopFn,
            //返回顶部
            return_top: function () {
                ds.scrollTo(0, 0);
                hideReturnTop();
            }
        };
    quickShell.delegate('a', 'click', function (e) {
        var type = getHandlerType(this.className);
        if (type && quickHandlers[type]) {
            quickHandlers[type].call(this);
            e.preventDefault();
        }
    });

    //Return top
    var scrollTimer, resizeTimer, minWidth = 1350;

    function resizeHandler() {
        clearTimeout(scrollTimer);
        scrollTimer = setTimeout(checkScroll, 160);
    }

    function checkResize() {
        quickShell[view.width() > 1340 ? 'removeClass' : 'addClass']('quick_links_dockright');
    }

    function scrollHandler() {
        clearTimeout(resizeTimer);
        resizeTimer = setTimeout(checkResize, 160);
    }

    function checkScroll() {
        view.scrollTop() > 100 ? showReturnTop() : hideReturnTop();
    }

    function showReturnTop() {
        quickPanel.addClass('quick_links_allow_gotop');
    }

    function hideReturnTop() {
        quickPanel.removeClass('quick_links_allow_gotop');
    }

    view.bind('scroll.go_top', resizeHandler).bind('resize.quick_links', scrollHandler);
    quickLinkCollapsed && quickShell.addClass('quick_links_min');
    resizeHandler();
    scrollHandler();

    $("button").click(function(){
        $("p").toggle();
    });
}


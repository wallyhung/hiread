hi.regionService = {
    getProvince: function (id) {
        var province = _.find(hi.regionService.provinces, {'id': id});
        if (province) {
            return province.name;
        } else {
            return '';
        }
    },
    getCity: function (provinceId, id) {
        var cities = hi.regionService.provinceCities[provinceId];
        var city = _.find(cities, {'id': id});
        if (city) {
            return city.name;
        } else {
            return '';
        }
    },
    getArea: function (cityId, id) {
        var areas = hi.regionService.cityAreas[cityId];
        var area = _.find(areas, {'id': id});
        if (area) {
            return area.name;
        } else {
            return '';
        }
    },
    provinces: [
        {
            "name": "上海市",
            "id": "310000"
        },
        {
            "name": "北京市",
            "id": "110000"
        }, {
            "name": "天津市",
            "id": "120000"
        }, {
            "name": "河北省",
            "id": "130000"
        }, {
            "name": "山西省",
            "id": "140000"
        }, {
            "name": "内蒙古自治区",
            "id": "150000"
        }, {
            "name": "辽宁省",
            "id": "210000"
        }, {
            "name": "吉林省",
            "id": "220000"
        }, {
            "name": "黑龙江省",
            "id": "230000"
        }, {
            "name": "江苏省",
            "id": "320000"
        }, {
            "name": "浙江省",
            "id": "330000"
        }, {
            "name": "安徽省",
            "id": "340000"
        }, {
            "name": "福建省",
            "id": "350000"
        }, {
            "name": "江西省",
            "id": "360000"
        }, {
            "name": "山东省",
            "id": "370000"
        }, {
            "name": "河南省",
            "id": "410000"
        }, {
            "name": "湖北省",
            "id": "420000"
        }, {
            "name": "湖南省",
            "id": "430000"
        }, {
            "name": "广东省",
            "id": "440000"
        }, {
            "name": "广西壮族自治区",
            "id": "450000"
        }, {
            "name": "海南省",
            "id": "460000"
        }, {
            "name": "重庆市",
            "id": "500000"
        }, {
            "name": "四川省",
            "id": "510000"
        }, {
            "name": "贵州省",
            "id": "520000"
        }, {
            "name": "云南省",
            "id": "530000"
        }, {
            "name": "西藏自治区",
            "id": "540000"
        }, {
            "name": "陕西省",
            "id": "610000"
        }, {
            "name": "甘肃省",
            "id": "620000"
        }, {
            "name": "青海省",
            "id": "630000"
        }, {
            "name": "宁夏回族自治区",
            "id": "640000"
        }, {
            "name": "新疆维吾尔自治区",
            "id": "650000"
        }, {
            "name": "台湾省",
            "id": "710000"
        }, {
            "name": "香港特别行政区",
            "id": "810000"
        }, {
            "name": "澳门特别行政区",
            "id": "820000"
        }],

    provinceCities: {
        "110000": [
            {
                "province": "北京市",
                "name": "市辖区",
                "id": "110100"
            }
        ],
        "120000": [
            {
                "province": "天津市",
                "name": "市辖区",
                "id": "120100"
            }
        ],
        "130000": [
            {
                "province": "河北省",
                "name": "石家庄市",
                "id": "130100"
            },
            {
                "province": "河北省",
                "name": "唐山市",
                "id": "130200"
            },
            {
                "province": "河北省",
                "name": "秦皇岛市",
                "id": "130300"
            },
            {
                "province": "河北省",
                "name": "邯郸市",
                "id": "130400"
            },
            {
                "province": "河北省",
                "name": "邢台市",
                "id": "130500"
            },
            {
                "province": "河北省",
                "name": "保定市",
                "id": "130600"
            },
            {
                "province": "河北省",
                "name": "张家口市",
                "id": "130700"
            },
            {
                "province": "河北省",
                "name": "承德市",
                "id": "130800"
            },
            {
                "province": "河北省",
                "name": "沧州市",
                "id": "130900"
            },
            {
                "province": "河北省",
                "name": "廊坊市",
                "id": "131000"
            },
            {
                "province": "河北省",
                "name": "衡水市",
                "id": "131100"
            },
            {
                "province": "河北省",
                "name": "省直辖县级行政区划",
                "id": "139000"
            }
        ],
        "140000": [
            {
                "province": "山西省",
                "name": "太原市",
                "id": "140100"
            },
            {
                "province": "山西省",
                "name": "大同市",
                "id": "140200"
            },
            {
                "province": "山西省",
                "name": "阳泉市",
                "id": "140300"
            },
            {
                "province": "山西省",
                "name": "长治市",
                "id": "140400"
            },
            {
                "province": "山西省",
                "name": "晋城市",
                "id": "140500"
            },
            {
                "province": "山西省",
                "name": "朔州市",
                "id": "140600"
            },
            {
                "province": "山西省",
                "name": "晋中市",
                "id": "140700"
            },
            {
                "province": "山西省",
                "name": "运城市",
                "id": "140800"
            },
            {
                "province": "山西省",
                "name": "忻州市",
                "id": "140900"
            },
            {
                "province": "山西省",
                "name": "临汾市",
                "id": "141000"
            },
            {
                "province": "山西省",
                "name": "吕梁市",
                "id": "141100"
            }
        ],
        "150000": [
            {
                "province": "内蒙古自治区",
                "name": "呼和浩特市",
                "id": "150100"
            },
            {
                "province": "内蒙古自治区",
                "name": "包头市",
                "id": "150200"
            },
            {
                "province": "内蒙古自治区",
                "name": "乌海市",
                "id": "150300"
            },
            {
                "province": "内蒙古自治区",
                "name": "赤峰市",
                "id": "150400"
            },
            {
                "province": "内蒙古自治区",
                "name": "通辽市",
                "id": "150500"
            },
            {
                "province": "内蒙古自治区",
                "name": "鄂尔多斯市",
                "id": "150600"
            },
            {
                "province": "内蒙古自治区",
                "name": "呼伦贝尔市",
                "id": "150700"
            },
            {
                "province": "内蒙古自治区",
                "name": "巴彦淖尔市",
                "id": "150800"
            },
            {
                "province": "内蒙古自治区",
                "name": "乌兰察布市",
                "id": "150900"
            },
            {
                "province": "内蒙古自治区",
                "name": "兴安盟",
                "id": "152200"
            },
            {
                "province": "内蒙古自治区",
                "name": "锡林郭勒盟",
                "id": "152500"
            },
            {
                "province": "内蒙古自治区",
                "name": "阿拉善盟",
                "id": "152900"
            }
        ],
        "210000": [
            {
                "province": "辽宁省",
                "name": "沈阳市",
                "id": "210100"
            },
            {
                "province": "辽宁省",
                "name": "大连市",
                "id": "210200"
            },
            {
                "province": "辽宁省",
                "name": "鞍山市",
                "id": "210300"
            },
            {
                "province": "辽宁省",
                "name": "抚顺市",
                "id": "210400"
            },
            {
                "province": "辽宁省",
                "name": "本溪市",
                "id": "210500"
            },
            {
                "province": "辽宁省",
                "name": "丹东市",
                "id": "210600"
            },
            {
                "province": "辽宁省",
                "name": "锦州市",
                "id": "210700"
            },
            {
                "province": "辽宁省",
                "name": "营口市",
                "id": "210800"
            },
            {
                "province": "辽宁省",
                "name": "阜新市",
                "id": "210900"
            },
            {
                "province": "辽宁省",
                "name": "辽阳市",
                "id": "211000"
            },
            {
                "province": "辽宁省",
                "name": "盘锦市",
                "id": "211100"
            },
            {
                "province": "辽宁省",
                "name": "铁岭市",
                "id": "211200"
            },
            {
                "province": "辽宁省",
                "name": "朝阳市",
                "id": "211300"
            },
            {
                "province": "辽宁省",
                "name": "葫芦岛市",
                "id": "211400"
            }
        ],
        "220000": [
            {
                "province": "吉林省",
                "name": "长春市",
                "id": "220100"
            },
            {
                "province": "吉林省",
                "name": "吉林市",
                "id": "220200"
            },
            {
                "province": "吉林省",
                "name": "四平市",
                "id": "220300"
            },
            {
                "province": "吉林省",
                "name": "辽源市",
                "id": "220400"
            },
            {
                "province": "吉林省",
                "name": "通化市",
                "id": "220500"
            },
            {
                "province": "吉林省",
                "name": "白山市",
                "id": "220600"
            },
            {
                "province": "吉林省",
                "name": "松原市",
                "id": "220700"
            },
            {
                "province": "吉林省",
                "name": "白城市",
                "id": "220800"
            },
            {
                "province": "吉林省",
                "name": "延边朝鲜族自治州",
                "id": "222400"
            }
        ],
        "230000": [
            {
                "province": "黑龙江省",
                "name": "哈尔滨市",
                "id": "230100"
            },
            {
                "province": "黑龙江省",
                "name": "齐齐哈尔市",
                "id": "230200"
            },
            {
                "province": "黑龙江省",
                "name": "鸡西市",
                "id": "230300"
            },
            {
                "province": "黑龙江省",
                "name": "鹤岗市",
                "id": "230400"
            },
            {
                "province": "黑龙江省",
                "name": "双鸭山市",
                "id": "230500"
            },
            {
                "province": "黑龙江省",
                "name": "大庆市",
                "id": "230600"
            },
            {
                "province": "黑龙江省",
                "name": "伊春市",
                "id": "230700"
            },
            {
                "province": "黑龙江省",
                "name": "佳木斯市",
                "id": "230800"
            },
            {
                "province": "黑龙江省",
                "name": "七台河市",
                "id": "230900"
            },
            {
                "province": "黑龙江省",
                "name": "牡丹江市",
                "id": "231000"
            },
            {
                "province": "黑龙江省",
                "name": "黑河市",
                "id": "231100"
            },
            {
                "province": "黑龙江省",
                "name": "绥化市",
                "id": "231200"
            },
            {
                "province": "黑龙江省",
                "name": "大兴安岭地区",
                "id": "232700"
            }
        ],
        "310000": [
            {
                "province": "上海市",
                "name": "市辖区",
                "id": "310100"
            }
        ],
        "320000": [
            {
                "province": "江苏省",
                "name": "南京市",
                "id": "320100"
            },
            {
                "province": "江苏省",
                "name": "无锡市",
                "id": "320200"
            },
            {
                "province": "江苏省",
                "name": "徐州市",
                "id": "320300"
            },
            {
                "province": "江苏省",
                "name": "常州市",
                "id": "320400"
            },
            {
                "province": "江苏省",
                "name": "苏州市",
                "id": "320500"
            },
            {
                "province": "江苏省",
                "name": "南通市",
                "id": "320600"
            },
            {
                "province": "江苏省",
                "name": "连云港市",
                "id": "320700"
            },
            {
                "province": "江苏省",
                "name": "淮安市",
                "id": "320800"
            },
            {
                "province": "江苏省",
                "name": "盐城市",
                "id": "320900"
            },
            {
                "province": "江苏省",
                "name": "扬州市",
                "id": "321000"
            },
            {
                "province": "江苏省",
                "name": "镇江市",
                "id": "321100"
            },
            {
                "province": "江苏省",
                "name": "泰州市",
                "id": "321200"
            },
            {
                "province": "江苏省",
                "name": "宿迁市",
                "id": "321300"
            }
        ],
        "330000": [
            {
                "province": "浙江省",
                "name": "杭州市",
                "id": "330100"
            },
            {
                "province": "浙江省",
                "name": "宁波市",
                "id": "330200"
            },
            {
                "province": "浙江省",
                "name": "温州市",
                "id": "330300"
            },
            {
                "province": "浙江省",
                "name": "嘉兴市",
                "id": "330400"
            },
            {
                "province": "浙江省",
                "name": "湖州市",
                "id": "330500"
            },
            {
                "province": "浙江省",
                "name": "绍兴市",
                "id": "330600"
            },
            {
                "province": "浙江省",
                "name": "金华市",
                "id": "330700"
            },
            {
                "province": "浙江省",
                "name": "衢州市",
                "id": "330800"
            },
            {
                "province": "浙江省",
                "name": "舟山市",
                "id": "330900"
            },
            {
                "province": "浙江省",
                "name": "台州市",
                "id": "331000"
            },
            {
                "province": "浙江省",
                "name": "丽水市",
                "id": "331100"
            }
        ],
        "340000": [
            {
                "province": "安徽省",
                "name": "合肥市",
                "id": "340100"
            },
            {
                "province": "安徽省",
                "name": "芜湖市",
                "id": "340200"
            },
            {
                "province": "安徽省",
                "name": "蚌埠市",
                "id": "340300"
            },
            {
                "province": "安徽省",
                "name": "淮南市",
                "id": "340400"
            },
            {
                "province": "安徽省",
                "name": "马鞍山市",
                "id": "340500"
            },
            {
                "province": "安徽省",
                "name": "淮北市",
                "id": "340600"
            },
            {
                "province": "安徽省",
                "name": "铜陵市",
                "id": "340700"
            },
            {
                "province": "安徽省",
                "name": "安庆市",
                "id": "340800"
            },
            {
                "province": "安徽省",
                "name": "黄山市",
                "id": "341000"
            },
            {
                "province": "安徽省",
                "name": "滁州市",
                "id": "341100"
            },
            {
                "province": "安徽省",
                "name": "阜阳市",
                "id": "341200"
            },
            {
                "province": "安徽省",
                "name": "宿州市",
                "id": "341300"
            },
            {
                "province": "安徽省",
                "name": "六安市",
                "id": "341500"
            },
            {
                "province": "安徽省",
                "name": "亳州市",
                "id": "341600"
            },
            {
                "province": "安徽省",
                "name": "池州市",
                "id": "341700"
            },
            {
                "province": "安徽省",
                "name": "宣城市",
                "id": "341800"
            }
        ],
        "350000": [
            {
                "province": "福建省",
                "name": "福州市",
                "id": "350100"
            },
            {
                "province": "福建省",
                "name": "厦门市",
                "id": "350200"
            },
            {
                "province": "福建省",
                "name": "莆田市",
                "id": "350300"
            },
            {
                "province": "福建省",
                "name": "三明市",
                "id": "350400"
            },
            {
                "province": "福建省",
                "name": "泉州市",
                "id": "350500"
            },
            {
                "province": "福建省",
                "name": "漳州市",
                "id": "350600"
            },
            {
                "province": "福建省",
                "name": "南平市",
                "id": "350700"
            },
            {
                "province": "福建省",
                "name": "龙岩市",
                "id": "350800"
            },
            {
                "province": "福建省",
                "name": "宁德市",
                "id": "350900"
            }
        ],
        "360000": [
            {
                "province": "江西省",
                "name": "南昌市",
                "id": "360100"
            },
            {
                "province": "江西省",
                "name": "景德镇市",
                "id": "360200"
            },
            {
                "province": "江西省",
                "name": "萍乡市",
                "id": "360300"
            },
            {
                "province": "江西省",
                "name": "九江市",
                "id": "360400"
            },
            {
                "province": "江西省",
                "name": "新余市",
                "id": "360500"
            },
            {
                "province": "江西省",
                "name": "鹰潭市",
                "id": "360600"
            },
            {
                "province": "江西省",
                "name": "赣州市",
                "id": "360700"
            },
            {
                "province": "江西省",
                "name": "吉安市",
                "id": "360800"
            },
            {
                "province": "江西省",
                "name": "宜春市",
                "id": "360900"
            },
            {
                "province": "江西省",
                "name": "抚州市",
                "id": "361000"
            },
            {
                "province": "江西省",
                "name": "上饶市",
                "id": "361100"
            }
        ],
        "370000": [
            {
                "province": "山东省",
                "name": "济南市",
                "id": "370100"
            },
            {
                "province": "山东省",
                "name": "青岛市",
                "id": "370200"
            },
            {
                "province": "山东省",
                "name": "淄博市",
                "id": "370300"
            },
            {
                "province": "山东省",
                "name": "枣庄市",
                "id": "370400"
            },
            {
                "province": "山东省",
                "name": "东营市",
                "id": "370500"
            },
            {
                "province": "山东省",
                "name": "烟台市",
                "id": "370600"
            },
            {
                "province": "山东省",
                "name": "潍坊市",
                "id": "370700"
            },
            {
                "province": "山东省",
                "name": "济宁市",
                "id": "370800"
            },
            {
                "province": "山东省",
                "name": "泰安市",
                "id": "370900"
            },
            {
                "province": "山东省",
                "name": "威海市",
                "id": "371000"
            },
            {
                "province": "山东省",
                "name": "日照市",
                "id": "371100"
            },
            {
                "province": "山东省",
                "name": "莱芜市",
                "id": "371200"
            },
            {
                "province": "山东省",
                "name": "临沂市",
                "id": "371300"
            },
            {
                "province": "山东省",
                "name": "德州市",
                "id": "371400"
            },
            {
                "province": "山东省",
                "name": "聊城市",
                "id": "371500"
            },
            {
                "province": "山东省",
                "name": "滨州市",
                "id": "371600"
            },
            {
                "province": "山东省",
                "name": "菏泽市",
                "id": "371700"
            }
        ],
        "410000": [
            {
                "province": "河南省",
                "name": "郑州市",
                "id": "410100"
            },
            {
                "province": "河南省",
                "name": "开封市",
                "id": "410200"
            },
            {
                "province": "河南省",
                "name": "洛阳市",
                "id": "410300"
            },
            {
                "province": "河南省",
                "name": "平顶山市",
                "id": "410400"
            },
            {
                "province": "河南省",
                "name": "安阳市",
                "id": "410500"
            },
            {
                "province": "河南省",
                "name": "鹤壁市",
                "id": "410600"
            },
            {
                "province": "河南省",
                "name": "新乡市",
                "id": "410700"
            },
            {
                "province": "河南省",
                "name": "焦作市",
                "id": "410800"
            },
            {
                "province": "河南省",
                "name": "濮阳市",
                "id": "410900"
            },
            {
                "province": "河南省",
                "name": "许昌市",
                "id": "411000"
            },
            {
                "province": "河南省",
                "name": "漯河市",
                "id": "411100"
            },
            {
                "province": "河南省",
                "name": "三门峡市",
                "id": "411200"
            },
            {
                "province": "河南省",
                "name": "南阳市",
                "id": "411300"
            },
            {
                "province": "河南省",
                "name": "商丘市",
                "id": "411400"
            },
            {
                "province": "河南省",
                "name": "信阳市",
                "id": "411500"
            },
            {
                "province": "河南省",
                "name": "周口市",
                "id": "411600"
            },
            {
                "province": "河南省",
                "name": "驻马店市",
                "id": "411700"
            },
            {
                "province": "河南省",
                "name": "省直辖县级行政区划",
                "id": "419000"
            }
        ],
        "420000": [
            {
                "province": "湖北省",
                "name": "武汉市",
                "id": "420100"
            },
            {
                "province": "湖北省",
                "name": "黄石市",
                "id": "420200"
            },
            {
                "province": "湖北省",
                "name": "十堰市",
                "id": "420300"
            },
            {
                "province": "湖北省",
                "name": "宜昌市",
                "id": "420500"
            },
            {
                "province": "湖北省",
                "name": "襄阳市",
                "id": "420600"
            },
            {
                "province": "湖北省",
                "name": "鄂州市",
                "id": "420700"
            },
            {
                "province": "湖北省",
                "name": "荆门市",
                "id": "420800"
            },
            {
                "province": "湖北省",
                "name": "孝感市",
                "id": "420900"
            },
            {
                "province": "湖北省",
                "name": "荆州市",
                "id": "421000"
            },
            {
                "province": "湖北省",
                "name": "黄冈市",
                "id": "421100"
            },
            {
                "province": "湖北省",
                "name": "咸宁市",
                "id": "421200"
            },
            {
                "province": "湖北省",
                "name": "随州市",
                "id": "421300"
            },
            {
                "province": "湖北省",
                "name": "恩施土家族苗族自治州",
                "id": "422800"
            },
            {
                "province": "湖北省",
                "name": "省直辖县级行政区划",
                "id": "429000"
            }
        ],
        "430000": [
            {
                "province": "湖南省",
                "name": "长沙市",
                "id": "430100"
            },
            {
                "province": "湖南省",
                "name": "株洲市",
                "id": "430200"
            },
            {
                "province": "湖南省",
                "name": "湘潭市",
                "id": "430300"
            },
            {
                "province": "湖南省",
                "name": "衡阳市",
                "id": "430400"
            },
            {
                "province": "湖南省",
                "name": "邵阳市",
                "id": "430500"
            },
            {
                "province": "湖南省",
                "name": "岳阳市",
                "id": "430600"
            },
            {
                "province": "湖南省",
                "name": "常德市",
                "id": "430700"
            },
            {
                "province": "湖南省",
                "name": "张家界市",
                "id": "430800"
            },
            {
                "province": "湖南省",
                "name": "益阳市",
                "id": "430900"
            },
            {
                "province": "湖南省",
                "name": "郴州市",
                "id": "431000"
            },
            {
                "province": "湖南省",
                "name": "永州市",
                "id": "431100"
            },
            {
                "province": "湖南省",
                "name": "怀化市",
                "id": "431200"
            },
            {
                "province": "湖南省",
                "name": "娄底市",
                "id": "431300"
            },
            {
                "province": "湖南省",
                "name": "湘西土家族苗族自治州",
                "id": "433100"
            }
        ],
        "440000": [
            {
                "province": "广东省",
                "name": "广州市",
                "id": "440100"
            },
            {
                "province": "广东省",
                "name": "韶关市",
                "id": "440200"
            },
            {
                "province": "广东省",
                "name": "深圳市",
                "id": "440300"
            },
            {
                "province": "广东省",
                "name": "珠海市",
                "id": "440400"
            },
            {
                "province": "广东省",
                "name": "汕头市",
                "id": "440500"
            },
            {
                "province": "广东省",
                "name": "佛山市",
                "id": "440600"
            },
            {
                "province": "广东省",
                "name": "江门市",
                "id": "440700"
            },
            {
                "province": "广东省",
                "name": "湛江市",
                "id": "440800"
            },
            {
                "province": "广东省",
                "name": "茂名市",
                "id": "440900"
            },
            {
                "province": "广东省",
                "name": "肇庆市",
                "id": "441200"
            },
            {
                "province": "广东省",
                "name": "惠州市",
                "id": "441300"
            },
            {
                "province": "广东省",
                "name": "梅州市",
                "id": "441400"
            },
            {
                "province": "广东省",
                "name": "汕尾市",
                "id": "441500"
            },
            {
                "province": "广东省",
                "name": "河源市",
                "id": "441600"
            },
            {
                "province": "广东省",
                "name": "阳江市",
                "id": "441700"
            },
            {
                "province": "广东省",
                "name": "清远市",
                "id": "441800"
            },
            {
                "province": "广东省",
                "name": "东莞市",
                "id": "441900"
            },
            {
                "province": "广东省",
                "name": "中山市",
                "id": "442000"
            },
            {
                "province": "广东省",
                "name": "潮州市",
                "id": "445100"
            },
            {
                "province": "广东省",
                "name": "揭阳市",
                "id": "445200"
            },
            {
                "province": "广东省",
                "name": "云浮市",
                "id": "445300"
            }
        ],
        "450000": [
            {
                "province": "广西壮族自治区",
                "name": "南宁市",
                "id": "450100"
            },
            {
                "province": "广西壮族自治区",
                "name": "柳州市",
                "id": "450200"
            },
            {
                "province": "广西壮族自治区",
                "name": "桂林市",
                "id": "450300"
            },
            {
                "province": "广西壮族自治区",
                "name": "梧州市",
                "id": "450400"
            },
            {
                "province": "广西壮族自治区",
                "name": "北海市",
                "id": "450500"
            },
            {
                "province": "广西壮族自治区",
                "name": "防城港市",
                "id": "450600"
            },
            {
                "province": "广西壮族自治区",
                "name": "钦州市",
                "id": "450700"
            },
            {
                "province": "广西壮族自治区",
                "name": "贵港市",
                "id": "450800"
            },
            {
                "province": "广西壮族自治区",
                "name": "玉林市",
                "id": "450900"
            },
            {
                "province": "广西壮族自治区",
                "name": "百色市",
                "id": "451000"
            },
            {
                "province": "广西壮族自治区",
                "name": "贺州市",
                "id": "451100"
            },
            {
                "province": "广西壮族自治区",
                "name": "河池市",
                "id": "451200"
            },
            {
                "province": "广西壮族自治区",
                "name": "来宾市",
                "id": "451300"
            },
            {
                "province": "广西壮族自治区",
                "name": "崇左市",
                "id": "451400"
            }
        ],
        "460000": [
            {
                "province": "海南省",
                "name": "海口市",
                "id": "460100"
            },
            {
                "province": "海南省",
                "name": "三亚市",
                "id": "460200"
            },
            {
                "province": "海南省",
                "name": "三沙市",
                "id": "460300"
            },
            {
                "province": "海南省",
                "name": "儋州市",
                "id": "460400"
            },
            {
                "province": "海南省",
                "name": "省直辖县级行政区划",
                "id": "469000"
            }
        ],
        "500000": [
            {
                "province": "重庆市",
                "name": "市辖区",
                "id": "500100"
            },
            {
                "province": "重庆市",
                "name": "县",
                "id": "500200"
            }
        ],
        "510000": [
            {
                "province": "四川省",
                "name": "成都市",
                "id": "510100"
            },
            {
                "province": "四川省",
                "name": "自贡市",
                "id": "510300"
            },
            {
                "province": "四川省",
                "name": "攀枝花市",
                "id": "510400"
            },
            {
                "province": "四川省",
                "name": "泸州市",
                "id": "510500"
            },
            {
                "province": "四川省",
                "name": "德阳市",
                "id": "510600"
            },
            {
                "province": "四川省",
                "name": "绵阳市",
                "id": "510700"
            },
            {
                "province": "四川省",
                "name": "广元市",
                "id": "510800"
            },
            {
                "province": "四川省",
                "name": "遂宁市",
                "id": "510900"
            },
            {
                "province": "四川省",
                "name": "内江市",
                "id": "511000"
            },
            {
                "province": "四川省",
                "name": "乐山市",
                "id": "511100"
            },
            {
                "province": "四川省",
                "name": "南充市",
                "id": "511300"
            },
            {
                "province": "四川省",
                "name": "眉山市",
                "id": "511400"
            },
            {
                "province": "四川省",
                "name": "宜宾市",
                "id": "511500"
            },
            {
                "province": "四川省",
                "name": "广安市",
                "id": "511600"
            },
            {
                "province": "四川省",
                "name": "达州市",
                "id": "511700"
            },
            {
                "province": "四川省",
                "name": "雅安市",
                "id": "511800"
            },
            {
                "province": "四川省",
                "name": "巴中市",
                "id": "511900"
            },
            {
                "province": "四川省",
                "name": "资阳市",
                "id": "512000"
            },
            {
                "province": "四川省",
                "name": "阿坝藏族羌族自治州",
                "id": "513200"
            },
            {
                "province": "四川省",
                "name": "甘孜藏族自治州",
                "id": "513300"
            },
            {
                "province": "四川省",
                "name": "凉山彝族自治州",
                "id": "513400"
            }
        ],
        "520000": [
            {
                "province": "贵州省",
                "name": "贵阳市",
                "id": "520100"
            },
            {
                "province": "贵州省",
                "name": "六盘水市",
                "id": "520200"
            },
            {
                "province": "贵州省",
                "name": "遵义市",
                "id": "520300"
            },
            {
                "province": "贵州省",
                "name": "安顺市",
                "id": "520400"
            },
            {
                "province": "贵州省",
                "name": "毕节市",
                "id": "520500"
            },
            {
                "province": "贵州省",
                "name": "铜仁市",
                "id": "520600"
            },
            {
                "province": "贵州省",
                "name": "黔西南布依族苗族自治州",
                "id": "522300"
            },
            {
                "province": "贵州省",
                "name": "黔东南苗族侗族自治州",
                "id": "522600"
            },
            {
                "province": "贵州省",
                "name": "黔南布依族苗族自治州",
                "id": "522700"
            }
        ],
        "530000": [
            {
                "province": "云南省",
                "name": "昆明市",
                "id": "530100"
            },
            {
                "province": "云南省",
                "name": "曲靖市",
                "id": "530300"
            },
            {
                "province": "云南省",
                "name": "玉溪市",
                "id": "530400"
            },
            {
                "province": "云南省",
                "name": "保山市",
                "id": "530500"
            },
            {
                "province": "云南省",
                "name": "昭通市",
                "id": "530600"
            },
            {
                "province": "云南省",
                "name": "丽江市",
                "id": "530700"
            },
            {
                "province": "云南省",
                "name": "普洱市",
                "id": "530800"
            },
            {
                "province": "云南省",
                "name": "临沧市",
                "id": "530900"
            },
            {
                "province": "云南省",
                "name": "楚雄彝族自治州",
                "id": "532300"
            },
            {
                "province": "云南省",
                "name": "红河哈尼族彝族自治州",
                "id": "532500"
            },
            {
                "province": "云南省",
                "name": "文山壮族苗族自治州",
                "id": "532600"
            },
            {
                "province": "云南省",
                "name": "西双版纳傣族自治州",
                "id": "532800"
            },
            {
                "province": "云南省",
                "name": "大理白族自治州",
                "id": "532900"
            },
            {
                "province": "云南省",
                "name": "德宏傣族景颇族自治州",
                "id": "533100"
            },
            {
                "province": "云南省",
                "name": "怒江傈僳族自治州",
                "id": "533300"
            },
            {
                "province": "云南省",
                "name": "迪庆藏族自治州",
                "id": "533400"
            }
        ],
        "540000": [
            {
                "province": "西藏自治区",
                "name": "拉萨市",
                "id": "540100"
            },
            {
                "province": "西藏自治区",
                "name": "日喀则市",
                "id": "540200"
            },
            {
                "province": "西藏自治区",
                "name": "昌都市",
                "id": "540300"
            },
            {
                "province": "西藏自治区",
                "name": "林芝市",
                "id": "540400"
            },
            {
                "province": "西藏自治区",
                "name": "山南市",
                "id": "540500"
            },
            {
                "province": "西藏自治区",
                "name": "那曲地区",
                "id": "542400"
            },
            {
                "province": "西藏自治区",
                "name": "阿里地区",
                "id": "542500"
            }
        ],
        "610000": [
            {
                "province": "陕西省",
                "name": "西安市",
                "id": "610100"
            },
            {
                "province": "陕西省",
                "name": "铜川市",
                "id": "610200"
            },
            {
                "province": "陕西省",
                "name": "宝鸡市",
                "id": "610300"
            },
            {
                "province": "陕西省",
                "name": "咸阳市",
                "id": "610400"
            },
            {
                "province": "陕西省",
                "name": "渭南市",
                "id": "610500"
            },
            {
                "province": "陕西省",
                "name": "延安市",
                "id": "610600"
            },
            {
                "province": "陕西省",
                "name": "汉中市",
                "id": "610700"
            },
            {
                "province": "陕西省",
                "name": "榆林市",
                "id": "610800"
            },
            {
                "province": "陕西省",
                "name": "安康市",
                "id": "610900"
            },
            {
                "province": "陕西省",
                "name": "商洛市",
                "id": "611000"
            }
        ],
        "620000": [
            {
                "province": "甘肃省",
                "name": "兰州市",
                "id": "620100"
            },
            {
                "province": "甘肃省",
                "name": "嘉峪关市",
                "id": "620200"
            },
            {
                "province": "甘肃省",
                "name": "金昌市",
                "id": "620300"
            },
            {
                "province": "甘肃省",
                "name": "白银市",
                "id": "620400"
            },
            {
                "province": "甘肃省",
                "name": "天水市",
                "id": "620500"
            },
            {
                "province": "甘肃省",
                "name": "武威市",
                "id": "620600"
            },
            {
                "province": "甘肃省",
                "name": "张掖市",
                "id": "620700"
            },
            {
                "province": "甘肃省",
                "name": "平凉市",
                "id": "620800"
            },
            {
                "province": "甘肃省",
                "name": "酒泉市",
                "id": "620900"
            },
            {
                "province": "甘肃省",
                "name": "庆阳市",
                "id": "621000"
            },
            {
                "province": "甘肃省",
                "name": "定西市",
                "id": "621100"
            },
            {
                "province": "甘肃省",
                "name": "陇南市",
                "id": "621200"
            },
            {
                "province": "甘肃省",
                "name": "临夏回族自治州",
                "id": "622900"
            },
            {
                "province": "甘肃省",
                "name": "甘南藏族自治州",
                "id": "623000"
            }
        ],
        "630000": [
            {
                "province": "青海省",
                "name": "西宁市",
                "id": "630100"
            },
            {
                "province": "青海省",
                "name": "海东市",
                "id": "630200"
            },
            {
                "province": "青海省",
                "name": "海北藏族自治州",
                "id": "632200"
            },
            {
                "province": "青海省",
                "name": "黄南藏族自治州",
                "id": "632300"
            },
            {
                "province": "青海省",
                "name": "海南藏族自治州",
                "id": "632500"
            },
            {
                "province": "青海省",
                "name": "果洛藏族自治州",
                "id": "632600"
            },
            {
                "province": "青海省",
                "name": "玉树藏族自治州",
                "id": "632700"
            },
            {
                "province": "青海省",
                "name": "海西蒙古族藏族自治州",
                "id": "632800"
            }
        ],
        "640000": [
            {
                "province": "宁夏回族自治区",
                "name": "银川市",
                "id": "640100"
            },
            {
                "province": "宁夏回族自治区",
                "name": "石嘴山市",
                "id": "640200"
            },
            {
                "province": "宁夏回族自治区",
                "name": "吴忠市",
                "id": "640300"
            },
            {
                "province": "宁夏回族自治区",
                "name": "固原市",
                "id": "640400"
            },
            {
                "province": "宁夏回族自治区",
                "name": "中卫市",
                "id": "640500"
            }
        ],
        "650000": [
            {
                "province": "新疆维吾尔自治区",
                "name": "乌鲁木齐市",
                "id": "650100"
            },
            {
                "province": "新疆维吾尔自治区",
                "name": "克拉玛依市",
                "id": "650200"
            },
            {
                "province": "新疆维吾尔自治区",
                "name": "吐鲁番市",
                "id": "650400"
            },
            {
                "province": "新疆维吾尔自治区",
                "name": "哈密市",
                "id": "650500"
            },
            {
                "province": "新疆维吾尔自治区",
                "name": "昌吉回族自治州",
                "id": "652300"
            },
            {
                "province": "新疆维吾尔自治区",
                "name": "博尔塔拉蒙古自治州",
                "id": "652700"
            },
            {
                "province": "新疆维吾尔自治区",
                "name": "巴音郭楞蒙古自治州",
                "id": "652800"
            },
            {
                "province": "新疆维吾尔自治区",
                "name": "阿克苏地区",
                "id": "652900"
            },
            {
                "province": "新疆维吾尔自治区",
                "name": "克孜勒苏柯尔克孜自治州",
                "id": "653000"
            },
            {
                "province": "新疆维吾尔自治区",
                "name": "喀什地区",
                "id": "653100"
            },
            {
                "province": "新疆维吾尔自治区",
                "name": "和田地区",
                "id": "653200"
            },
            {
                "province": "新疆维吾尔自治区",
                "name": "伊犁哈萨克自治州",
                "id": "654000"
            },
            {
                "province": "新疆维吾尔自治区",
                "name": "塔城地区",
                "id": "654200"
            },
            {
                "province": "新疆维吾尔自治区",
                "name": "阿勒泰地区",
                "id": "654300"
            },
            {
                "province": "新疆维吾尔自治区",
                "name": "自治区直辖县级行政区划",
                "id": "659000"
            }
        ]
    },
    cityAreas: {
        "110100": [
            {
                "city": "市辖区",
                "name": "东城区",
                "id": "110101"
            },
            {
                "city": "市辖区",
                "name": "西城区",
                "id": "110102"
            },
            {
                "city": "市辖区",
                "name": "朝阳区",
                "id": "110105"
            },
            {
                "city": "市辖区",
                "name": "丰台区",
                "id": "110106"
            },
            {
                "city": "市辖区",
                "name": "石景山区",
                "id": "110107"
            },
            {
                "city": "市辖区",
                "name": "海淀区",
                "id": "110108"
            },
            {
                "city": "市辖区",
                "name": "门头沟区",
                "id": "110109"
            },
            {
                "city": "市辖区",
                "name": "房山区",
                "id": "110111"
            },
            {
                "city": "市辖区",
                "name": "通州区",
                "id": "110112"
            },
            {
                "city": "市辖区",
                "name": "顺义区",
                "id": "110113"
            },
            {
                "city": "市辖区",
                "name": "昌平区",
                "id": "110114"
            },
            {
                "city": "市辖区",
                "name": "大兴区",
                "id": "110115"
            },
            {
                "city": "市辖区",
                "name": "怀柔区",
                "id": "110116"
            },
            {
                "city": "市辖区",
                "name": "平谷区",
                "id": "110117"
            },
            {
                "city": "市辖区",
                "name": "密云区",
                "id": "110118"
            },
            {
                "city": "市辖区",
                "name": "延庆区",
                "id": "110119"
            }
        ],
        "120100": [
            {
                "city": "市辖区",
                "name": "和平区",
                "id": "120101"
            },
            {
                "city": "市辖区",
                "name": "河东区",
                "id": "120102"
            },
            {
                "city": "市辖区",
                "name": "河西区",
                "id": "120103"
            },
            {
                "city": "市辖区",
                "name": "南开区",
                "id": "120104"
            },
            {
                "city": "市辖区",
                "name": "河北区",
                "id": "120105"
            },
            {
                "city": "市辖区",
                "name": "红桥区",
                "id": "120106"
            },
            {
                "city": "市辖区",
                "name": "东丽区",
                "id": "120110"
            },
            {
                "city": "市辖区",
                "name": "西青区",
                "id": "120111"
            },
            {
                "city": "市辖区",
                "name": "津南区",
                "id": "120112"
            },
            {
                "city": "市辖区",
                "name": "北辰区",
                "id": "120113"
            },
            {
                "city": "市辖区",
                "name": "武清区",
                "id": "120114"
            },
            {
                "city": "市辖区",
                "name": "宝坻区",
                "id": "120115"
            },
            {
                "city": "市辖区",
                "name": "滨海新区",
                "id": "120116"
            },
            {
                "city": "市辖区",
                "name": "宁河区",
                "id": "120117"
            },
            {
                "city": "市辖区",
                "name": "静海区",
                "id": "120118"
            },
            {
                "city": "市辖区",
                "name": "蓟州区",
                "id": "120119"
            }
        ],
        "130100": [
            {
                "city": "石家庄市",
                "name": "市辖区",
                "id": "130101"
            },
            {
                "city": "石家庄市",
                "name": "长安区",
                "id": "130102"
            },
            {
                "city": "石家庄市",
                "name": "桥西区",
                "id": "130104"
            },
            {
                "city": "石家庄市",
                "name": "新华区",
                "id": "130105"
            },
            {
                "city": "石家庄市",
                "name": "井陉矿区",
                "id": "130107"
            },
            {
                "city": "石家庄市",
                "name": "裕华区",
                "id": "130108"
            },
            {
                "city": "石家庄市",
                "name": "藁城区",
                "id": "130109"
            },
            {
                "city": "石家庄市",
                "name": "鹿泉区",
                "id": "130110"
            },
            {
                "city": "石家庄市",
                "name": "栾城区",
                "id": "130111"
            },
            {
                "city": "石家庄市",
                "name": "井陉县",
                "id": "130121"
            },
            {
                "city": "石家庄市",
                "name": "正定县",
                "id": "130123"
            },
            {
                "city": "石家庄市",
                "name": "行唐县",
                "id": "130125"
            },
            {
                "city": "石家庄市",
                "name": "灵寿县",
                "id": "130126"
            },
            {
                "city": "石家庄市",
                "name": "高邑县",
                "id": "130127"
            },
            {
                "city": "石家庄市",
                "name": "深泽县",
                "id": "130128"
            },
            {
                "city": "石家庄市",
                "name": "赞皇县",
                "id": "130129"
            },
            {
                "city": "石家庄市",
                "name": "无极县",
                "id": "130130"
            },
            {
                "city": "石家庄市",
                "name": "平山县",
                "id": "130131"
            },
            {
                "city": "石家庄市",
                "name": "元氏县",
                "id": "130132"
            },
            {
                "city": "石家庄市",
                "name": "赵县",
                "id": "130133"
            },
            {
                "city": "石家庄市",
                "name": "晋州市",
                "id": "130183"
            },
            {
                "city": "石家庄市",
                "name": "新乐市",
                "id": "130184"
            }
        ],
        "130200": [
            {
                "city": "唐山市",
                "name": "市辖区",
                "id": "130201"
            },
            {
                "city": "唐山市",
                "name": "路南区",
                "id": "130202"
            },
            {
                "city": "唐山市",
                "name": "路北区",
                "id": "130203"
            },
            {
                "city": "唐山市",
                "name": "古冶区",
                "id": "130204"
            },
            {
                "city": "唐山市",
                "name": "开平区",
                "id": "130205"
            },
            {
                "city": "唐山市",
                "name": "丰南区",
                "id": "130207"
            },
            {
                "city": "唐山市",
                "name": "丰润区",
                "id": "130208"
            },
            {
                "city": "唐山市",
                "name": "曹妃甸区",
                "id": "130209"
            },
            {
                "city": "唐山市",
                "name": "滦县",
                "id": "130223"
            },
            {
                "city": "唐山市",
                "name": "滦南县",
                "id": "130224"
            },
            {
                "city": "唐山市",
                "name": "乐亭县",
                "id": "130225"
            },
            {
                "city": "唐山市",
                "name": "迁西县",
                "id": "130227"
            },
            {
                "city": "唐山市",
                "name": "玉田县",
                "id": "130229"
            },
            {
                "city": "唐山市",
                "name": "遵化市",
                "id": "130281"
            },
            {
                "city": "唐山市",
                "name": "迁安市",
                "id": "130283"
            }
        ],
        "130300": [
            {
                "city": "秦皇岛市",
                "name": "市辖区",
                "id": "130301"
            },
            {
                "city": "秦皇岛市",
                "name": "海港区",
                "id": "130302"
            },
            {
                "city": "秦皇岛市",
                "name": "山海关区",
                "id": "130303"
            },
            {
                "city": "秦皇岛市",
                "name": "北戴河区",
                "id": "130304"
            },
            {
                "city": "秦皇岛市",
                "name": "抚宁区",
                "id": "130306"
            },
            {
                "city": "秦皇岛市",
                "name": "青龙满族自治县",
                "id": "130321"
            },
            {
                "city": "秦皇岛市",
                "name": "昌黎县",
                "id": "130322"
            },
            {
                "city": "秦皇岛市",
                "name": "卢龙县",
                "id": "130324"
            }
        ],
        "130400": [
            {
                "city": "邯郸市",
                "name": "市辖区",
                "id": "130401"
            },
            {
                "city": "邯郸市",
                "name": "邯山区",
                "id": "130402"
            },
            {
                "city": "邯郸市",
                "name": "丛台区",
                "id": "130403"
            },
            {
                "city": "邯郸市",
                "name": "复兴区",
                "id": "130404"
            },
            {
                "city": "邯郸市",
                "name": "峰峰矿区",
                "id": "130406"
            },
            {
                "city": "邯郸市",
                "name": "邯郸县",
                "id": "130421"
            },
            {
                "city": "邯郸市",
                "name": "临漳县",
                "id": "130423"
            },
            {
                "city": "邯郸市",
                "name": "成安县",
                "id": "130424"
            },
            {
                "city": "邯郸市",
                "name": "大名县",
                "id": "130425"
            },
            {
                "city": "邯郸市",
                "name": "涉县",
                "id": "130426"
            },
            {
                "city": "邯郸市",
                "name": "磁县",
                "id": "130427"
            },
            {
                "city": "邯郸市",
                "name": "肥乡县",
                "id": "130428"
            },
            {
                "city": "邯郸市",
                "name": "永年县",
                "id": "130429"
            },
            {
                "city": "邯郸市",
                "name": "邱县",
                "id": "130430"
            },
            {
                "city": "邯郸市",
                "name": "鸡泽县",
                "id": "130431"
            },
            {
                "city": "邯郸市",
                "name": "广平县",
                "id": "130432"
            },
            {
                "city": "邯郸市",
                "name": "馆陶县",
                "id": "130433"
            },
            {
                "city": "邯郸市",
                "name": "魏县",
                "id": "130434"
            },
            {
                "city": "邯郸市",
                "name": "曲周县",
                "id": "130435"
            },
            {
                "city": "邯郸市",
                "name": "武安市",
                "id": "130481"
            }
        ],
        "130500": [
            {
                "city": "邢台市",
                "name": "市辖区",
                "id": "130501"
            },
            {
                "city": "邢台市",
                "name": "桥东区",
                "id": "130502"
            },
            {
                "city": "邢台市",
                "name": "桥西区",
                "id": "130503"
            },
            {
                "city": "邢台市",
                "name": "邢台县",
                "id": "130521"
            },
            {
                "city": "邢台市",
                "name": "临城县",
                "id": "130522"
            },
            {
                "city": "邢台市",
                "name": "内丘县",
                "id": "130523"
            },
            {
                "city": "邢台市",
                "name": "柏乡县",
                "id": "130524"
            },
            {
                "city": "邢台市",
                "name": "隆尧县",
                "id": "130525"
            },
            {
                "city": "邢台市",
                "name": "任县",
                "id": "130526"
            },
            {
                "city": "邢台市",
                "name": "南和县",
                "id": "130527"
            },
            {
                "city": "邢台市",
                "name": "宁晋县",
                "id": "130528"
            },
            {
                "city": "邢台市",
                "name": "巨鹿县",
                "id": "130529"
            },
            {
                "city": "邢台市",
                "name": "新河县",
                "id": "130530"
            },
            {
                "city": "邢台市",
                "name": "广宗县",
                "id": "130531"
            },
            {
                "city": "邢台市",
                "name": "平乡县",
                "id": "130532"
            },
            {
                "city": "邢台市",
                "name": "威县",
                "id": "130533"
            },
            {
                "city": "邢台市",
                "name": "清河县",
                "id": "130534"
            },
            {
                "city": "邢台市",
                "name": "临西县",
                "id": "130535"
            },
            {
                "city": "邢台市",
                "name": "南宫市",
                "id": "130581"
            },
            {
                "city": "邢台市",
                "name": "沙河市",
                "id": "130582"
            }
        ],
        "130600": [
            {
                "city": "保定市",
                "name": "市辖区",
                "id": "130601"
            },
            {
                "city": "保定市",
                "name": "竞秀区",
                "id": "130602"
            },
            {
                "city": "保定市",
                "name": "莲池区",
                "id": "130606"
            },
            {
                "city": "保定市",
                "name": "满城区",
                "id": "130607"
            },
            {
                "city": "保定市",
                "name": "清苑区",
                "id": "130608"
            },
            {
                "city": "保定市",
                "name": "徐水区",
                "id": "130609"
            },
            {
                "city": "保定市",
                "name": "涞水县",
                "id": "130623"
            },
            {
                "city": "保定市",
                "name": "阜平县",
                "id": "130624"
            },
            {
                "city": "保定市",
                "name": "定兴县",
                "id": "130626"
            },
            {
                "city": "保定市",
                "name": "唐县",
                "id": "130627"
            },
            {
                "city": "保定市",
                "name": "高阳县",
                "id": "130628"
            },
            {
                "city": "保定市",
                "name": "容城县",
                "id": "130629"
            },
            {
                "city": "保定市",
                "name": "涞源县",
                "id": "130630"
            },
            {
                "city": "保定市",
                "name": "望都县",
                "id": "130631"
            },
            {
                "city": "保定市",
                "name": "安新县",
                "id": "130632"
            },
            {
                "city": "保定市",
                "name": "易县",
                "id": "130633"
            },
            {
                "city": "保定市",
                "name": "曲阳县",
                "id": "130634"
            },
            {
                "city": "保定市",
                "name": "蠡县",
                "id": "130635"
            },
            {
                "city": "保定市",
                "name": "顺平县",
                "id": "130636"
            },
            {
                "city": "保定市",
                "name": "博野县",
                "id": "130637"
            },
            {
                "city": "保定市",
                "name": "雄县",
                "id": "130638"
            },
            {
                "city": "保定市",
                "name": "涿州市",
                "id": "130681"
            },
            {
                "city": "保定市",
                "name": "安国市",
                "id": "130683"
            },
            {
                "city": "保定市",
                "name": "高碑店市",
                "id": "130684"
            }
        ],
        "130700": [
            {
                "city": "张家口市",
                "name": "市辖区",
                "id": "130701"
            },
            {
                "city": "张家口市",
                "name": "桥东区",
                "id": "130702"
            },
            {
                "city": "张家口市",
                "name": "桥西区",
                "id": "130703"
            },
            {
                "city": "张家口市",
                "name": "宣化区",
                "id": "130705"
            },
            {
                "city": "张家口市",
                "name": "下花园区",
                "id": "130706"
            },
            {
                "city": "张家口市",
                "name": "万全区",
                "id": "130708"
            },
            {
                "city": "张家口市",
                "name": "崇礼区",
                "id": "130709"
            },
            {
                "city": "张家口市",
                "name": "张北县",
                "id": "130722"
            },
            {
                "city": "张家口市",
                "name": "康保县",
                "id": "130723"
            },
            {
                "city": "张家口市",
                "name": "沽源县",
                "id": "130724"
            },
            {
                "city": "张家口市",
                "name": "尚义县",
                "id": "130725"
            },
            {
                "city": "张家口市",
                "name": "蔚县",
                "id": "130726"
            },
            {
                "city": "张家口市",
                "name": "阳原县",
                "id": "130727"
            },
            {
                "city": "张家口市",
                "name": "怀安县",
                "id": "130728"
            },
            {
                "city": "张家口市",
                "name": "怀来县",
                "id": "130730"
            },
            {
                "city": "张家口市",
                "name": "涿鹿县",
                "id": "130731"
            },
            {
                "city": "张家口市",
                "name": "赤城县",
                "id": "130732"
            }
        ],
        "130800": [
            {
                "city": "承德市",
                "name": "市辖区",
                "id": "130801"
            },
            {
                "city": "承德市",
                "name": "双桥区",
                "id": "130802"
            },
            {
                "city": "承德市",
                "name": "双滦区",
                "id": "130803"
            },
            {
                "city": "承德市",
                "name": "鹰手营子矿区",
                "id": "130804"
            },
            {
                "city": "承德市",
                "name": "承德县",
                "id": "130821"
            },
            {
                "city": "承德市",
                "name": "兴隆县",
                "id": "130822"
            },
            {
                "city": "承德市",
                "name": "平泉县",
                "id": "130823"
            },
            {
                "city": "承德市",
                "name": "滦平县",
                "id": "130824"
            },
            {
                "city": "承德市",
                "name": "隆化县",
                "id": "130825"
            },
            {
                "city": "承德市",
                "name": "丰宁满族自治县",
                "id": "130826"
            },
            {
                "city": "承德市",
                "name": "宽城满族自治县",
                "id": "130827"
            },
            {
                "city": "承德市",
                "name": "围场满族蒙古族自治县",
                "id": "130828"
            }
        ],
        "130900": [
            {
                "city": "沧州市",
                "name": "市辖区",
                "id": "130901"
            },
            {
                "city": "沧州市",
                "name": "新华区",
                "id": "130902"
            },
            {
                "city": "沧州市",
                "name": "运河区",
                "id": "130903"
            },
            {
                "city": "沧州市",
                "name": "沧县",
                "id": "130921"
            },
            {
                "city": "沧州市",
                "name": "青县",
                "id": "130922"
            },
            {
                "city": "沧州市",
                "name": "东光县",
                "id": "130923"
            },
            {
                "city": "沧州市",
                "name": "海兴县",
                "id": "130924"
            },
            {
                "city": "沧州市",
                "name": "盐山县",
                "id": "130925"
            },
            {
                "city": "沧州市",
                "name": "肃宁县",
                "id": "130926"
            },
            {
                "city": "沧州市",
                "name": "南皮县",
                "id": "130927"
            },
            {
                "city": "沧州市",
                "name": "吴桥县",
                "id": "130928"
            },
            {
                "city": "沧州市",
                "name": "献县",
                "id": "130929"
            },
            {
                "city": "沧州市",
                "name": "孟村回族自治县",
                "id": "130930"
            },
            {
                "city": "沧州市",
                "name": "泊头市",
                "id": "130981"
            },
            {
                "city": "沧州市",
                "name": "任丘市",
                "id": "130982"
            },
            {
                "city": "沧州市",
                "name": "黄骅市",
                "id": "130983"
            },
            {
                "city": "沧州市",
                "name": "河间市",
                "id": "130984"
            }
        ],
        "131000": [
            {
                "city": "廊坊市",
                "name": "市辖区",
                "id": "131001"
            },
            {
                "city": "廊坊市",
                "name": "安次区",
                "id": "131002"
            },
            {
                "city": "廊坊市",
                "name": "广阳区",
                "id": "131003"
            },
            {
                "city": "廊坊市",
                "name": "固安县",
                "id": "131022"
            },
            {
                "city": "廊坊市",
                "name": "永清县",
                "id": "131023"
            },
            {
                "city": "廊坊市",
                "name": "香河县",
                "id": "131024"
            },
            {
                "city": "廊坊市",
                "name": "大城县",
                "id": "131025"
            },
            {
                "city": "廊坊市",
                "name": "文安县",
                "id": "131026"
            },
            {
                "city": "廊坊市",
                "name": "大厂回族自治县",
                "id": "131028"
            },
            {
                "city": "廊坊市",
                "name": "霸州市",
                "id": "131081"
            },
            {
                "city": "廊坊市",
                "name": "三河市",
                "id": "131082"
            }
        ],
        "131100": [
            {
                "city": "衡水市",
                "name": "市辖区",
                "id": "131101"
            },
            {
                "city": "衡水市",
                "name": "桃城区",
                "id": "131102"
            },
            {
                "city": "衡水市",
                "name": "冀州区",
                "id": "131103"
            },
            {
                "city": "衡水市",
                "name": "枣强县",
                "id": "131121"
            },
            {
                "city": "衡水市",
                "name": "武邑县",
                "id": "131122"
            },
            {
                "city": "衡水市",
                "name": "武强县",
                "id": "131123"
            },
            {
                "city": "衡水市",
                "name": "饶阳县",
                "id": "131124"
            },
            {
                "city": "衡水市",
                "name": "安平县",
                "id": "131125"
            },
            {
                "city": "衡水市",
                "name": "故城县",
                "id": "131126"
            },
            {
                "city": "衡水市",
                "name": "景县",
                "id": "131127"
            },
            {
                "city": "衡水市",
                "name": "阜城县",
                "id": "131128"
            },
            {
                "city": "衡水市",
                "name": "深州市",
                "id": "131182"
            }
        ],
        "139000": [
            {
                "city": "省直辖县级行政区划",
                "name": "定州市",
                "id": "139001"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "辛集市",
                "id": "139002"
            }
        ],
        "140100": [
            {
                "city": "太原市",
                "name": "市辖区",
                "id": "140101"
            },
            {
                "city": "太原市",
                "name": "小店区",
                "id": "140105"
            },
            {
                "city": "太原市",
                "name": "迎泽区",
                "id": "140106"
            },
            {
                "city": "太原市",
                "name": "杏花岭区",
                "id": "140107"
            },
            {
                "city": "太原市",
                "name": "尖草坪区",
                "id": "140108"
            },
            {
                "city": "太原市",
                "name": "万柏林区",
                "id": "140109"
            },
            {
                "city": "太原市",
                "name": "晋源区",
                "id": "140110"
            },
            {
                "city": "太原市",
                "name": "清徐县",
                "id": "140121"
            },
            {
                "city": "太原市",
                "name": "阳曲县",
                "id": "140122"
            },
            {
                "city": "太原市",
                "name": "娄烦县",
                "id": "140123"
            },
            {
                "city": "太原市",
                "name": "古交市",
                "id": "140181"
            }
        ],
        "140200": [
            {
                "city": "大同市",
                "name": "市辖区",
                "id": "140201"
            },
            {
                "city": "大同市",
                "name": "城区",
                "id": "140202"
            },
            {
                "city": "大同市",
                "name": "矿区",
                "id": "140203"
            },
            {
                "city": "大同市",
                "name": "南郊区",
                "id": "140211"
            },
            {
                "city": "大同市",
                "name": "新荣区",
                "id": "140212"
            },
            {
                "city": "大同市",
                "name": "阳高县",
                "id": "140221"
            },
            {
                "city": "大同市",
                "name": "天镇县",
                "id": "140222"
            },
            {
                "city": "大同市",
                "name": "广灵县",
                "id": "140223"
            },
            {
                "city": "大同市",
                "name": "灵丘县",
                "id": "140224"
            },
            {
                "city": "大同市",
                "name": "浑源县",
                "id": "140225"
            },
            {
                "city": "大同市",
                "name": "左云县",
                "id": "140226"
            },
            {
                "city": "大同市",
                "name": "大同县",
                "id": "140227"
            }
        ],
        "140300": [
            {
                "city": "阳泉市",
                "name": "市辖区",
                "id": "140301"
            },
            {
                "city": "阳泉市",
                "name": "城区",
                "id": "140302"
            },
            {
                "city": "阳泉市",
                "name": "矿区",
                "id": "140303"
            },
            {
                "city": "阳泉市",
                "name": "郊区",
                "id": "140311"
            },
            {
                "city": "阳泉市",
                "name": "平定县",
                "id": "140321"
            },
            {
                "city": "阳泉市",
                "name": "盂县",
                "id": "140322"
            }
        ],
        "140400": [
            {
                "city": "长治市",
                "name": "市辖区",
                "id": "140401"
            },
            {
                "city": "长治市",
                "name": "城区",
                "id": "140402"
            },
            {
                "city": "长治市",
                "name": "郊区",
                "id": "140411"
            },
            {
                "city": "长治市",
                "name": "长治县",
                "id": "140421"
            },
            {
                "city": "长治市",
                "name": "襄垣县",
                "id": "140423"
            },
            {
                "city": "长治市",
                "name": "屯留县",
                "id": "140424"
            },
            {
                "city": "长治市",
                "name": "平顺县",
                "id": "140425"
            },
            {
                "city": "长治市",
                "name": "黎城县",
                "id": "140426"
            },
            {
                "city": "长治市",
                "name": "壶关县",
                "id": "140427"
            },
            {
                "city": "长治市",
                "name": "长子县",
                "id": "140428"
            },
            {
                "city": "长治市",
                "name": "武乡县",
                "id": "140429"
            },
            {
                "city": "长治市",
                "name": "沁县",
                "id": "140430"
            },
            {
                "city": "长治市",
                "name": "沁源县",
                "id": "140431"
            },
            {
                "city": "长治市",
                "name": "潞城市",
                "id": "140481"
            }
        ],
        "140500": [
            {
                "city": "晋城市",
                "name": "市辖区",
                "id": "140501"
            },
            {
                "city": "晋城市",
                "name": "城区",
                "id": "140502"
            },
            {
                "city": "晋城市",
                "name": "沁水县",
                "id": "140521"
            },
            {
                "city": "晋城市",
                "name": "阳城县",
                "id": "140522"
            },
            {
                "city": "晋城市",
                "name": "陵川县",
                "id": "140524"
            },
            {
                "city": "晋城市",
                "name": "泽州县",
                "id": "140525"
            },
            {
                "city": "晋城市",
                "name": "高平市",
                "id": "140581"
            }
        ],
        "140600": [
            {
                "city": "朔州市",
                "name": "市辖区",
                "id": "140601"
            },
            {
                "city": "朔州市",
                "name": "朔城区",
                "id": "140602"
            },
            {
                "city": "朔州市",
                "name": "平鲁区",
                "id": "140603"
            },
            {
                "city": "朔州市",
                "name": "山阴县",
                "id": "140621"
            },
            {
                "city": "朔州市",
                "name": "应县",
                "id": "140622"
            },
            {
                "city": "朔州市",
                "name": "右玉县",
                "id": "140623"
            },
            {
                "city": "朔州市",
                "name": "怀仁县",
                "id": "140624"
            }
        ],
        "140700": [
            {
                "city": "晋中市",
                "name": "市辖区",
                "id": "140701"
            },
            {
                "city": "晋中市",
                "name": "榆次区",
                "id": "140702"
            },
            {
                "city": "晋中市",
                "name": "榆社县",
                "id": "140721"
            },
            {
                "city": "晋中市",
                "name": "左权县",
                "id": "140722"
            },
            {
                "city": "晋中市",
                "name": "和顺县",
                "id": "140723"
            },
            {
                "city": "晋中市",
                "name": "昔阳县",
                "id": "140724"
            },
            {
                "city": "晋中市",
                "name": "寿阳县",
                "id": "140725"
            },
            {
                "city": "晋中市",
                "name": "太谷县",
                "id": "140726"
            },
            {
                "city": "晋中市",
                "name": "祁县",
                "id": "140727"
            },
            {
                "city": "晋中市",
                "name": "平遥县",
                "id": "140728"
            },
            {
                "city": "晋中市",
                "name": "灵石县",
                "id": "140729"
            },
            {
                "city": "晋中市",
                "name": "介休市",
                "id": "140781"
            }
        ],
        "140800": [
            {
                "city": "运城市",
                "name": "市辖区",
                "id": "140801"
            },
            {
                "city": "运城市",
                "name": "盐湖区",
                "id": "140802"
            },
            {
                "city": "运城市",
                "name": "临猗县",
                "id": "140821"
            },
            {
                "city": "运城市",
                "name": "万荣县",
                "id": "140822"
            },
            {
                "city": "运城市",
                "name": "闻喜县",
                "id": "140823"
            },
            {
                "city": "运城市",
                "name": "稷山县",
                "id": "140824"
            },
            {
                "city": "运城市",
                "name": "新绛县",
                "id": "140825"
            },
            {
                "city": "运城市",
                "name": "绛县",
                "id": "140826"
            },
            {
                "city": "运城市",
                "name": "垣曲县",
                "id": "140827"
            },
            {
                "city": "运城市",
                "name": "夏县",
                "id": "140828"
            },
            {
                "city": "运城市",
                "name": "平陆县",
                "id": "140829"
            },
            {
                "city": "运城市",
                "name": "芮城县",
                "id": "140830"
            },
            {
                "city": "运城市",
                "name": "永济市",
                "id": "140881"
            },
            {
                "city": "运城市",
                "name": "河津市",
                "id": "140882"
            }
        ],
        "140900": [
            {
                "city": "忻州市",
                "name": "市辖区",
                "id": "140901"
            },
            {
                "city": "忻州市",
                "name": "忻府区",
                "id": "140902"
            },
            {
                "city": "忻州市",
                "name": "定襄县",
                "id": "140921"
            },
            {
                "city": "忻州市",
                "name": "五台县",
                "id": "140922"
            },
            {
                "city": "忻州市",
                "name": "代县",
                "id": "140923"
            },
            {
                "city": "忻州市",
                "name": "繁峙县",
                "id": "140924"
            },
            {
                "city": "忻州市",
                "name": "宁武县",
                "id": "140925"
            },
            {
                "city": "忻州市",
                "name": "静乐县",
                "id": "140926"
            },
            {
                "city": "忻州市",
                "name": "神池县",
                "id": "140927"
            },
            {
                "city": "忻州市",
                "name": "五寨县",
                "id": "140928"
            },
            {
                "city": "忻州市",
                "name": "岢岚县",
                "id": "140929"
            },
            {
                "city": "忻州市",
                "name": "河曲县",
                "id": "140930"
            },
            {
                "city": "忻州市",
                "name": "保德县",
                "id": "140931"
            },
            {
                "city": "忻州市",
                "name": "偏关县",
                "id": "140932"
            },
            {
                "city": "忻州市",
                "name": "原平市",
                "id": "140981"
            }
        ],
        "141000": [
            {
                "city": "临汾市",
                "name": "市辖区",
                "id": "141001"
            },
            {
                "city": "临汾市",
                "name": "尧都区",
                "id": "141002"
            },
            {
                "city": "临汾市",
                "name": "曲沃县",
                "id": "141021"
            },
            {
                "city": "临汾市",
                "name": "翼城县",
                "id": "141022"
            },
            {
                "city": "临汾市",
                "name": "襄汾县",
                "id": "141023"
            },
            {
                "city": "临汾市",
                "name": "洪洞县",
                "id": "141024"
            },
            {
                "city": "临汾市",
                "name": "古县",
                "id": "141025"
            },
            {
                "city": "临汾市",
                "name": "安泽县",
                "id": "141026"
            },
            {
                "city": "临汾市",
                "name": "浮山县",
                "id": "141027"
            },
            {
                "city": "临汾市",
                "name": "吉县",
                "id": "141028"
            },
            {
                "city": "临汾市",
                "name": "乡宁县",
                "id": "141029"
            },
            {
                "city": "临汾市",
                "name": "大宁县",
                "id": "141030"
            },
            {
                "city": "临汾市",
                "name": "隰县",
                "id": "141031"
            },
            {
                "city": "临汾市",
                "name": "永和县",
                "id": "141032"
            },
            {
                "city": "临汾市",
                "name": "蒲县",
                "id": "141033"
            },
            {
                "city": "临汾市",
                "name": "汾西县",
                "id": "141034"
            },
            {
                "city": "临汾市",
                "name": "侯马市",
                "id": "141081"
            },
            {
                "city": "临汾市",
                "name": "霍州市",
                "id": "141082"
            }
        ],
        "141100": [
            {
                "city": "吕梁市",
                "name": "市辖区",
                "id": "141101"
            },
            {
                "city": "吕梁市",
                "name": "离石区",
                "id": "141102"
            },
            {
                "city": "吕梁市",
                "name": "文水县",
                "id": "141121"
            },
            {
                "city": "吕梁市",
                "name": "交城县",
                "id": "141122"
            },
            {
                "city": "吕梁市",
                "name": "兴县",
                "id": "141123"
            },
            {
                "city": "吕梁市",
                "name": "临县",
                "id": "141124"
            },
            {
                "city": "吕梁市",
                "name": "柳林县",
                "id": "141125"
            },
            {
                "city": "吕梁市",
                "name": "石楼县",
                "id": "141126"
            },
            {
                "city": "吕梁市",
                "name": "岚县",
                "id": "141127"
            },
            {
                "city": "吕梁市",
                "name": "方山县",
                "id": "141128"
            },
            {
                "city": "吕梁市",
                "name": "中阳县",
                "id": "141129"
            },
            {
                "city": "吕梁市",
                "name": "交口县",
                "id": "141130"
            },
            {
                "city": "吕梁市",
                "name": "孝义市",
                "id": "141181"
            },
            {
                "city": "吕梁市",
                "name": "汾阳市",
                "id": "141182"
            }
        ],
        "150100": [
            {
                "city": "呼和浩特市",
                "name": "市辖区",
                "id": "150101"
            },
            {
                "city": "呼和浩特市",
                "name": "新城区",
                "id": "150102"
            },
            {
                "city": "呼和浩特市",
                "name": "回民区",
                "id": "150103"
            },
            {
                "city": "呼和浩特市",
                "name": "玉泉区",
                "id": "150104"
            },
            {
                "city": "呼和浩特市",
                "name": "赛罕区",
                "id": "150105"
            },
            {
                "city": "呼和浩特市",
                "name": "土默特左旗",
                "id": "150121"
            },
            {
                "city": "呼和浩特市",
                "name": "托克托县",
                "id": "150122"
            },
            {
                "city": "呼和浩特市",
                "name": "和林格尔县",
                "id": "150123"
            },
            {
                "city": "呼和浩特市",
                "name": "清水河县",
                "id": "150124"
            },
            {
                "city": "呼和浩特市",
                "name": "武川县",
                "id": "150125"
            }
        ],
        "150200": [
            {
                "city": "包头市",
                "name": "市辖区",
                "id": "150201"
            },
            {
                "city": "包头市",
                "name": "东河区",
                "id": "150202"
            },
            {
                "city": "包头市",
                "name": "昆都仑区",
                "id": "150203"
            },
            {
                "city": "包头市",
                "name": "青山区",
                "id": "150204"
            },
            {
                "city": "包头市",
                "name": "石拐区",
                "id": "150205"
            },
            {
                "city": "包头市",
                "name": "白云鄂博矿区",
                "id": "150206"
            },
            {
                "city": "包头市",
                "name": "九原区",
                "id": "150207"
            },
            {
                "city": "包头市",
                "name": "土默特右旗",
                "id": "150221"
            },
            {
                "city": "包头市",
                "name": "固阳县",
                "id": "150222"
            },
            {
                "city": "包头市",
                "name": "达尔罕茂明安联合旗",
                "id": "150223"
            }
        ],
        "150300": [
            {
                "city": "乌海市",
                "name": "市辖区",
                "id": "150301"
            },
            {
                "city": "乌海市",
                "name": "海勃湾区",
                "id": "150302"
            },
            {
                "city": "乌海市",
                "name": "海南区",
                "id": "150303"
            },
            {
                "city": "乌海市",
                "name": "乌达区",
                "id": "150304"
            }
        ],
        "150400": [
            {
                "city": "赤峰市",
                "name": "市辖区",
                "id": "150401"
            },
            {
                "city": "赤峰市",
                "name": "红山区",
                "id": "150402"
            },
            {
                "city": "赤峰市",
                "name": "元宝山区",
                "id": "150403"
            },
            {
                "city": "赤峰市",
                "name": "松山区",
                "id": "150404"
            },
            {
                "city": "赤峰市",
                "name": "阿鲁科尔沁旗",
                "id": "150421"
            },
            {
                "city": "赤峰市",
                "name": "巴林左旗",
                "id": "150422"
            },
            {
                "city": "赤峰市",
                "name": "巴林右旗",
                "id": "150423"
            },
            {
                "city": "赤峰市",
                "name": "林西县",
                "id": "150424"
            },
            {
                "city": "赤峰市",
                "name": "克什克腾旗",
                "id": "150425"
            },
            {
                "city": "赤峰市",
                "name": "翁牛特旗",
                "id": "150426"
            },
            {
                "city": "赤峰市",
                "name": "喀喇沁旗",
                "id": "150428"
            },
            {
                "city": "赤峰市",
                "name": "宁城县",
                "id": "150429"
            },
            {
                "city": "赤峰市",
                "name": "敖汉旗",
                "id": "150430"
            }
        ],
        "150500": [
            {
                "city": "通辽市",
                "name": "市辖区",
                "id": "150501"
            },
            {
                "city": "通辽市",
                "name": "科尔沁区",
                "id": "150502"
            },
            {
                "city": "通辽市",
                "name": "科尔沁左翼中旗",
                "id": "150521"
            },
            {
                "city": "通辽市",
                "name": "科尔沁左翼后旗",
                "id": "150522"
            },
            {
                "city": "通辽市",
                "name": "开鲁县",
                "id": "150523"
            },
            {
                "city": "通辽市",
                "name": "库伦旗",
                "id": "150524"
            },
            {
                "city": "通辽市",
                "name": "奈曼旗",
                "id": "150525"
            },
            {
                "city": "通辽市",
                "name": "扎鲁特旗",
                "id": "150526"
            },
            {
                "city": "通辽市",
                "name": "霍林郭勒市",
                "id": "150581"
            }
        ],
        "150600": [
            {
                "city": "鄂尔多斯市",
                "name": "市辖区",
                "id": "150601"
            },
            {
                "city": "鄂尔多斯市",
                "name": "东胜区",
                "id": "150602"
            },
            {
                "city": "鄂尔多斯市",
                "name": "康巴什区",
                "id": "150603"
            },
            {
                "city": "鄂尔多斯市",
                "name": "达拉特旗",
                "id": "150621"
            },
            {
                "city": "鄂尔多斯市",
                "name": "准格尔旗",
                "id": "150622"
            },
            {
                "city": "鄂尔多斯市",
                "name": "鄂托克前旗",
                "id": "150623"
            },
            {
                "city": "鄂尔多斯市",
                "name": "鄂托克旗",
                "id": "150624"
            },
            {
                "city": "鄂尔多斯市",
                "name": "杭锦旗",
                "id": "150625"
            },
            {
                "city": "鄂尔多斯市",
                "name": "乌审旗",
                "id": "150626"
            },
            {
                "city": "鄂尔多斯市",
                "name": "伊金霍洛旗",
                "id": "150627"
            }
        ],
        "150700": [
            {
                "city": "呼伦贝尔市",
                "name": "市辖区",
                "id": "150701"
            },
            {
                "city": "呼伦贝尔市",
                "name": "海拉尔区",
                "id": "150702"
            },
            {
                "city": "呼伦贝尔市",
                "name": "扎赉诺尔区",
                "id": "150703"
            },
            {
                "city": "呼伦贝尔市",
                "name": "阿荣旗",
                "id": "150721"
            },
            {
                "city": "呼伦贝尔市",
                "name": "莫力达瓦达斡尔族自治旗",
                "id": "150722"
            },
            {
                "city": "呼伦贝尔市",
                "name": "鄂伦春自治旗",
                "id": "150723"
            },
            {
                "city": "呼伦贝尔市",
                "name": "鄂温克族自治旗",
                "id": "150724"
            },
            {
                "city": "呼伦贝尔市",
                "name": "陈巴尔虎旗",
                "id": "150725"
            },
            {
                "city": "呼伦贝尔市",
                "name": "新巴尔虎左旗",
                "id": "150726"
            },
            {
                "city": "呼伦贝尔市",
                "name": "新巴尔虎右旗",
                "id": "150727"
            },
            {
                "city": "呼伦贝尔市",
                "name": "满洲里市",
                "id": "150781"
            },
            {
                "city": "呼伦贝尔市",
                "name": "牙克石市",
                "id": "150782"
            },
            {
                "city": "呼伦贝尔市",
                "name": "扎兰屯市",
                "id": "150783"
            },
            {
                "city": "呼伦贝尔市",
                "name": "额尔古纳市",
                "id": "150784"
            },
            {
                "city": "呼伦贝尔市",
                "name": "根河市",
                "id": "150785"
            }
        ],
        "150800": [
            {
                "city": "巴彦淖尔市",
                "name": "市辖区",
                "id": "150801"
            },
            {
                "city": "巴彦淖尔市",
                "name": "临河区",
                "id": "150802"
            },
            {
                "city": "巴彦淖尔市",
                "name": "五原县",
                "id": "150821"
            },
            {
                "city": "巴彦淖尔市",
                "name": "磴口县",
                "id": "150822"
            },
            {
                "city": "巴彦淖尔市",
                "name": "乌拉特前旗",
                "id": "150823"
            },
            {
                "city": "巴彦淖尔市",
                "name": "乌拉特中旗",
                "id": "150824"
            },
            {
                "city": "巴彦淖尔市",
                "name": "乌拉特后旗",
                "id": "150825"
            },
            {
                "city": "巴彦淖尔市",
                "name": "杭锦后旗",
                "id": "150826"
            }
        ],
        "150900": [
            {
                "city": "乌兰察布市",
                "name": "市辖区",
                "id": "150901"
            },
            {
                "city": "乌兰察布市",
                "name": "集宁区",
                "id": "150902"
            },
            {
                "city": "乌兰察布市",
                "name": "卓资县",
                "id": "150921"
            },
            {
                "city": "乌兰察布市",
                "name": "化德县",
                "id": "150922"
            },
            {
                "city": "乌兰察布市",
                "name": "商都县",
                "id": "150923"
            },
            {
                "city": "乌兰察布市",
                "name": "兴和县",
                "id": "150924"
            },
            {
                "city": "乌兰察布市",
                "name": "凉城县",
                "id": "150925"
            },
            {
                "city": "乌兰察布市",
                "name": "察哈尔右翼前旗",
                "id": "150926"
            },
            {
                "city": "乌兰察布市",
                "name": "察哈尔右翼中旗",
                "id": "150927"
            },
            {
                "city": "乌兰察布市",
                "name": "察哈尔右翼后旗",
                "id": "150928"
            },
            {
                "city": "乌兰察布市",
                "name": "四子王旗",
                "id": "150929"
            },
            {
                "city": "乌兰察布市",
                "name": "丰镇市",
                "id": "150981"
            }
        ],
        "152200": [
            {
                "city": "兴安盟",
                "name": "乌兰浩特市",
                "id": "152201"
            },
            {
                "city": "兴安盟",
                "name": "阿尔山市",
                "id": "152202"
            },
            {
                "city": "兴安盟",
                "name": "科尔沁右翼前旗",
                "id": "152221"
            },
            {
                "city": "兴安盟",
                "name": "科尔沁右翼中旗",
                "id": "152222"
            },
            {
                "city": "兴安盟",
                "name": "扎赉特旗",
                "id": "152223"
            },
            {
                "city": "兴安盟",
                "name": "突泉县",
                "id": "152224"
            }
        ],
        "152500": [
            {
                "city": "锡林郭勒盟",
                "name": "二连浩特市",
                "id": "152501"
            },
            {
                "city": "锡林郭勒盟",
                "name": "锡林浩特市",
                "id": "152502"
            },
            {
                "city": "锡林郭勒盟",
                "name": "阿巴嘎旗",
                "id": "152522"
            },
            {
                "city": "锡林郭勒盟",
                "name": "苏尼特左旗",
                "id": "152523"
            },
            {
                "city": "锡林郭勒盟",
                "name": "苏尼特右旗",
                "id": "152524"
            },
            {
                "city": "锡林郭勒盟",
                "name": "东乌珠穆沁旗",
                "id": "152525"
            },
            {
                "city": "锡林郭勒盟",
                "name": "西乌珠穆沁旗",
                "id": "152526"
            },
            {
                "city": "锡林郭勒盟",
                "name": "太仆寺旗",
                "id": "152527"
            },
            {
                "city": "锡林郭勒盟",
                "name": "镶黄旗",
                "id": "152528"
            },
            {
                "city": "锡林郭勒盟",
                "name": "正镶白旗",
                "id": "152529"
            },
            {
                "city": "锡林郭勒盟",
                "name": "正蓝旗",
                "id": "152530"
            },
            {
                "city": "锡林郭勒盟",
                "name": "多伦县",
                "id": "152531"
            }
        ],
        "152900": [
            {
                "city": "阿拉善盟",
                "name": "阿拉善左旗",
                "id": "152921"
            },
            {
                "city": "阿拉善盟",
                "name": "阿拉善右旗",
                "id": "152922"
            },
            {
                "city": "阿拉善盟",
                "name": "额济纳旗",
                "id": "152923"
            }
        ],
        "210100": [
            {
                "city": "沈阳市",
                "name": "市辖区",
                "id": "210101"
            },
            {
                "city": "沈阳市",
                "name": "和平区",
                "id": "210102"
            },
            {
                "city": "沈阳市",
                "name": "沈河区",
                "id": "210103"
            },
            {
                "city": "沈阳市",
                "name": "大东区",
                "id": "210104"
            },
            {
                "city": "沈阳市",
                "name": "皇姑区",
                "id": "210105"
            },
            {
                "city": "沈阳市",
                "name": "铁西区",
                "id": "210106"
            },
            {
                "city": "沈阳市",
                "name": "苏家屯区",
                "id": "210111"
            },
            {
                "city": "沈阳市",
                "name": "浑南区",
                "id": "210112"
            },
            {
                "city": "沈阳市",
                "name": "沈北新区",
                "id": "210113"
            },
            {
                "city": "沈阳市",
                "name": "于洪区",
                "id": "210114"
            },
            {
                "city": "沈阳市",
                "name": "辽中区",
                "id": "210115"
            },
            {
                "city": "沈阳市",
                "name": "康平县",
                "id": "210123"
            },
            {
                "city": "沈阳市",
                "name": "法库县",
                "id": "210124"
            },
            {
                "city": "沈阳市",
                "name": "新民市",
                "id": "210181"
            }
        ],
        "210200": [
            {
                "city": "大连市",
                "name": "市辖区",
                "id": "210201"
            },
            {
                "city": "大连市",
                "name": "中山区",
                "id": "210202"
            },
            {
                "city": "大连市",
                "name": "西岗区",
                "id": "210203"
            },
            {
                "city": "大连市",
                "name": "沙河口区",
                "id": "210204"
            },
            {
                "city": "大连市",
                "name": "甘井子区",
                "id": "210211"
            },
            {
                "city": "大连市",
                "name": "旅顺口区",
                "id": "210212"
            },
            {
                "city": "大连市",
                "name": "金州区",
                "id": "210213"
            },
            {
                "city": "大连市",
                "name": "普兰店区",
                "id": "210214"
            },
            {
                "city": "大连市",
                "name": "长海县",
                "id": "210224"
            },
            {
                "city": "大连市",
                "name": "瓦房店市",
                "id": "210281"
            },
            {
                "city": "大连市",
                "name": "庄河市",
                "id": "210283"
            }
        ],
        "210300": [
            {
                "city": "鞍山市",
                "name": "市辖区",
                "id": "210301"
            },
            {
                "city": "鞍山市",
                "name": "铁东区",
                "id": "210302"
            },
            {
                "city": "鞍山市",
                "name": "铁西区",
                "id": "210303"
            },
            {
                "city": "鞍山市",
                "name": "立山区",
                "id": "210304"
            },
            {
                "city": "鞍山市",
                "name": "千山区",
                "id": "210311"
            },
            {
                "city": "鞍山市",
                "name": "台安县",
                "id": "210321"
            },
            {
                "city": "鞍山市",
                "name": "岫岩满族自治县",
                "id": "210323"
            },
            {
                "city": "鞍山市",
                "name": "海城市",
                "id": "210381"
            }
        ],
        "210400": [
            {
                "city": "抚顺市",
                "name": "市辖区",
                "id": "210401"
            },
            {
                "city": "抚顺市",
                "name": "新抚区",
                "id": "210402"
            },
            {
                "city": "抚顺市",
                "name": "东洲区",
                "id": "210403"
            },
            {
                "city": "抚顺市",
                "name": "望花区",
                "id": "210404"
            },
            {
                "city": "抚顺市",
                "name": "顺城区",
                "id": "210411"
            },
            {
                "city": "抚顺市",
                "name": "抚顺县",
                "id": "210421"
            },
            {
                "city": "抚顺市",
                "name": "新宾满族自治县",
                "id": "210422"
            },
            {
                "city": "抚顺市",
                "name": "清原满族自治县",
                "id": "210423"
            }
        ],
        "210500": [
            {
                "city": "本溪市",
                "name": "市辖区",
                "id": "210501"
            },
            {
                "city": "本溪市",
                "name": "平山区",
                "id": "210502"
            },
            {
                "city": "本溪市",
                "name": "溪湖区",
                "id": "210503"
            },
            {
                "city": "本溪市",
                "name": "明山区",
                "id": "210504"
            },
            {
                "city": "本溪市",
                "name": "南芬区",
                "id": "210505"
            },
            {
                "city": "本溪市",
                "name": "本溪满族自治县",
                "id": "210521"
            },
            {
                "city": "本溪市",
                "name": "桓仁满族自治县",
                "id": "210522"
            }
        ],
        "210600": [
            {
                "city": "丹东市",
                "name": "市辖区",
                "id": "210601"
            },
            {
                "city": "丹东市",
                "name": "元宝区",
                "id": "210602"
            },
            {
                "city": "丹东市",
                "name": "振兴区",
                "id": "210603"
            },
            {
                "city": "丹东市",
                "name": "振安区",
                "id": "210604"
            },
            {
                "city": "丹东市",
                "name": "宽甸满族自治县",
                "id": "210624"
            },
            {
                "city": "丹东市",
                "name": "东港市",
                "id": "210681"
            },
            {
                "city": "丹东市",
                "name": "凤城市",
                "id": "210682"
            }
        ],
        "210700": [
            {
                "city": "锦州市",
                "name": "市辖区",
                "id": "210701"
            },
            {
                "city": "锦州市",
                "name": "古塔区",
                "id": "210702"
            },
            {
                "city": "锦州市",
                "name": "凌河区",
                "id": "210703"
            },
            {
                "city": "锦州市",
                "name": "太和区",
                "id": "210711"
            },
            {
                "city": "锦州市",
                "name": "黑山县",
                "id": "210726"
            },
            {
                "city": "锦州市",
                "name": "义县",
                "id": "210727"
            },
            {
                "city": "锦州市",
                "name": "凌海市",
                "id": "210781"
            },
            {
                "city": "锦州市",
                "name": "北镇市",
                "id": "210782"
            }
        ],
        "210800": [
            {
                "city": "营口市",
                "name": "市辖区",
                "id": "210801"
            },
            {
                "city": "营口市",
                "name": "站前区",
                "id": "210802"
            },
            {
                "city": "营口市",
                "name": "西市区",
                "id": "210803"
            },
            {
                "city": "营口市",
                "name": "鲅鱼圈区",
                "id": "210804"
            },
            {
                "city": "营口市",
                "name": "老边区",
                "id": "210811"
            },
            {
                "city": "营口市",
                "name": "盖州市",
                "id": "210881"
            },
            {
                "city": "营口市",
                "name": "大石桥市",
                "id": "210882"
            }
        ],
        "210900": [
            {
                "city": "阜新市",
                "name": "市辖区",
                "id": "210901"
            },
            {
                "city": "阜新市",
                "name": "海州区",
                "id": "210902"
            },
            {
                "city": "阜新市",
                "name": "新邱区",
                "id": "210903"
            },
            {
                "city": "阜新市",
                "name": "太平区",
                "id": "210904"
            },
            {
                "city": "阜新市",
                "name": "清河门区",
                "id": "210905"
            },
            {
                "city": "阜新市",
                "name": "细河区",
                "id": "210911"
            },
            {
                "city": "阜新市",
                "name": "阜新蒙古族自治县",
                "id": "210921"
            },
            {
                "city": "阜新市",
                "name": "彰武县",
                "id": "210922"
            }
        ],
        "211000": [
            {
                "city": "辽阳市",
                "name": "市辖区",
                "id": "211001"
            },
            {
                "city": "辽阳市",
                "name": "白塔区",
                "id": "211002"
            },
            {
                "city": "辽阳市",
                "name": "文圣区",
                "id": "211003"
            },
            {
                "city": "辽阳市",
                "name": "宏伟区",
                "id": "211004"
            },
            {
                "city": "辽阳市",
                "name": "弓长岭区",
                "id": "211005"
            },
            {
                "city": "辽阳市",
                "name": "太子河区",
                "id": "211011"
            },
            {
                "city": "辽阳市",
                "name": "辽阳县",
                "id": "211021"
            },
            {
                "city": "辽阳市",
                "name": "灯塔市",
                "id": "211081"
            }
        ],
        "211100": [
            {
                "city": "盘锦市",
                "name": "市辖区",
                "id": "211101"
            },
            {
                "city": "盘锦市",
                "name": "双台子区",
                "id": "211102"
            },
            {
                "city": "盘锦市",
                "name": "兴隆台区",
                "id": "211103"
            },
            {
                "city": "盘锦市",
                "name": "大洼区",
                "id": "211104"
            },
            {
                "city": "盘锦市",
                "name": "盘山县",
                "id": "211122"
            }
        ],
        "211200": [
            {
                "city": "铁岭市",
                "name": "市辖区",
                "id": "211201"
            },
            {
                "city": "铁岭市",
                "name": "银州区",
                "id": "211202"
            },
            {
                "city": "铁岭市",
                "name": "清河区",
                "id": "211204"
            },
            {
                "city": "铁岭市",
                "name": "铁岭县",
                "id": "211221"
            },
            {
                "city": "铁岭市",
                "name": "西丰县",
                "id": "211223"
            },
            {
                "city": "铁岭市",
                "name": "昌图县",
                "id": "211224"
            },
            {
                "city": "铁岭市",
                "name": "调兵山市",
                "id": "211281"
            },
            {
                "city": "铁岭市",
                "name": "开原市",
                "id": "211282"
            }
        ],
        "211300": [
            {
                "city": "朝阳市",
                "name": "市辖区",
                "id": "211301"
            },
            {
                "city": "朝阳市",
                "name": "双塔区",
                "id": "211302"
            },
            {
                "city": "朝阳市",
                "name": "龙城区",
                "id": "211303"
            },
            {
                "city": "朝阳市",
                "name": "朝阳县",
                "id": "211321"
            },
            {
                "city": "朝阳市",
                "name": "建平县",
                "id": "211322"
            },
            {
                "city": "朝阳市",
                "name": "喀喇沁左翼蒙古族自治县",
                "id": "211324"
            },
            {
                "city": "朝阳市",
                "name": "北票市",
                "id": "211381"
            },
            {
                "city": "朝阳市",
                "name": "凌源市",
                "id": "211382"
            }
        ],
        "211400": [
            {
                "city": "葫芦岛市",
                "name": "市辖区",
                "id": "211401"
            },
            {
                "city": "葫芦岛市",
                "name": "连山区",
                "id": "211402"
            },
            {
                "city": "葫芦岛市",
                "name": "龙港区",
                "id": "211403"
            },
            {
                "city": "葫芦岛市",
                "name": "南票区",
                "id": "211404"
            },
            {
                "city": "葫芦岛市",
                "name": "绥中县",
                "id": "211421"
            },
            {
                "city": "葫芦岛市",
                "name": "建昌县",
                "id": "211422"
            },
            {
                "city": "葫芦岛市",
                "name": "兴城市",
                "id": "211481"
            }
        ],
        "220100": [
            {
                "city": "长春市",
                "name": "市辖区",
                "id": "220101"
            },
            {
                "city": "长春市",
                "name": "南关区",
                "id": "220102"
            },
            {
                "city": "长春市",
                "name": "宽城区",
                "id": "220103"
            },
            {
                "city": "长春市",
                "name": "朝阳区",
                "id": "220104"
            },
            {
                "city": "长春市",
                "name": "二道区",
                "id": "220105"
            },
            {
                "city": "长春市",
                "name": "绿园区",
                "id": "220106"
            },
            {
                "city": "长春市",
                "name": "双阳区",
                "id": "220112"
            },
            {
                "city": "长春市",
                "name": "九台区",
                "id": "220113"
            },
            {
                "city": "长春市",
                "name": "农安县",
                "id": "220122"
            },
            {
                "city": "长春市",
                "name": "榆树市",
                "id": "220182"
            },
            {
                "city": "长春市",
                "name": "德惠市",
                "id": "220183"
            }
        ],
        "220200": [
            {
                "city": "吉林市",
                "name": "市辖区",
                "id": "220201"
            },
            {
                "city": "吉林市",
                "name": "昌邑区",
                "id": "220202"
            },
            {
                "city": "吉林市",
                "name": "龙潭区",
                "id": "220203"
            },
            {
                "city": "吉林市",
                "name": "船营区",
                "id": "220204"
            },
            {
                "city": "吉林市",
                "name": "丰满区",
                "id": "220211"
            },
            {
                "city": "吉林市",
                "name": "永吉县",
                "id": "220221"
            },
            {
                "city": "吉林市",
                "name": "蛟河市",
                "id": "220281"
            },
            {
                "city": "吉林市",
                "name": "桦甸市",
                "id": "220282"
            },
            {
                "city": "吉林市",
                "name": "舒兰市",
                "id": "220283"
            },
            {
                "city": "吉林市",
                "name": "磐石市",
                "id": "220284"
            }
        ],
        "220300": [
            {
                "city": "四平市",
                "name": "市辖区",
                "id": "220301"
            },
            {
                "city": "四平市",
                "name": "铁西区",
                "id": "220302"
            },
            {
                "city": "四平市",
                "name": "铁东区",
                "id": "220303"
            },
            {
                "city": "四平市",
                "name": "梨树县",
                "id": "220322"
            },
            {
                "city": "四平市",
                "name": "伊通满族自治县",
                "id": "220323"
            },
            {
                "city": "四平市",
                "name": "公主岭市",
                "id": "220381"
            },
            {
                "city": "四平市",
                "name": "双辽市",
                "id": "220382"
            }
        ],
        "220400": [
            {
                "city": "辽源市",
                "name": "市辖区",
                "id": "220401"
            },
            {
                "city": "辽源市",
                "name": "龙山区",
                "id": "220402"
            },
            {
                "city": "辽源市",
                "name": "西安区",
                "id": "220403"
            },
            {
                "city": "辽源市",
                "name": "东丰县",
                "id": "220421"
            },
            {
                "city": "辽源市",
                "name": "东辽县",
                "id": "220422"
            }
        ],
        "220500": [
            {
                "city": "通化市",
                "name": "市辖区",
                "id": "220501"
            },
            {
                "city": "通化市",
                "name": "东昌区",
                "id": "220502"
            },
            {
                "city": "通化市",
                "name": "二道江区",
                "id": "220503"
            },
            {
                "city": "通化市",
                "name": "通化县",
                "id": "220521"
            },
            {
                "city": "通化市",
                "name": "辉南县",
                "id": "220523"
            },
            {
                "city": "通化市",
                "name": "柳河县",
                "id": "220524"
            },
            {
                "city": "通化市",
                "name": "梅河口市",
                "id": "220581"
            },
            {
                "city": "通化市",
                "name": "集安市",
                "id": "220582"
            }
        ],
        "220600": [
            {
                "city": "白山市",
                "name": "市辖区",
                "id": "220601"
            },
            {
                "city": "白山市",
                "name": "浑江区",
                "id": "220602"
            },
            {
                "city": "白山市",
                "name": "江源区",
                "id": "220605"
            },
            {
                "city": "白山市",
                "name": "抚松县",
                "id": "220621"
            },
            {
                "city": "白山市",
                "name": "靖宇县",
                "id": "220622"
            },
            {
                "city": "白山市",
                "name": "长白朝鲜族自治县",
                "id": "220623"
            },
            {
                "city": "白山市",
                "name": "临江市",
                "id": "220681"
            }
        ],
        "220700": [
            {
                "city": "松原市",
                "name": "市辖区",
                "id": "220701"
            },
            {
                "city": "松原市",
                "name": "宁江区",
                "id": "220702"
            },
            {
                "city": "松原市",
                "name": "前郭尔罗斯蒙古族自治县",
                "id": "220721"
            },
            {
                "city": "松原市",
                "name": "长岭县",
                "id": "220722"
            },
            {
                "city": "松原市",
                "name": "乾安县",
                "id": "220723"
            },
            {
                "city": "松原市",
                "name": "扶余市",
                "id": "220781"
            }
        ],
        "220800": [
            {
                "city": "白城市",
                "name": "市辖区",
                "id": "220801"
            },
            {
                "city": "白城市",
                "name": "洮北区",
                "id": "220802"
            },
            {
                "city": "白城市",
                "name": "镇赉县",
                "id": "220821"
            },
            {
                "city": "白城市",
                "name": "通榆县",
                "id": "220822"
            },
            {
                "city": "白城市",
                "name": "洮南市",
                "id": "220881"
            },
            {
                "city": "白城市",
                "name": "大安市",
                "id": "220882"
            }
        ],
        "222400": [
            {
                "city": "延边朝鲜族自治州",
                "name": "延吉市",
                "id": "222401"
            },
            {
                "city": "延边朝鲜族自治州",
                "name": "图们市",
                "id": "222402"
            },
            {
                "city": "延边朝鲜族自治州",
                "name": "敦化市",
                "id": "222403"
            },
            {
                "city": "延边朝鲜族自治州",
                "name": "珲春市",
                "id": "222404"
            },
            {
                "city": "延边朝鲜族自治州",
                "name": "龙井市",
                "id": "222405"
            },
            {
                "city": "延边朝鲜族自治州",
                "name": "和龙市",
                "id": "222406"
            },
            {
                "city": "延边朝鲜族自治州",
                "name": "汪清县",
                "id": "222424"
            },
            {
                "city": "延边朝鲜族自治州",
                "name": "安图县",
                "id": "222426"
            }
        ],
        "230100": [
            {
                "city": "哈尔滨市",
                "name": "市辖区",
                "id": "230101"
            },
            {
                "city": "哈尔滨市",
                "name": "道里区",
                "id": "230102"
            },
            {
                "city": "哈尔滨市",
                "name": "南岗区",
                "id": "230103"
            },
            {
                "city": "哈尔滨市",
                "name": "道外区",
                "id": "230104"
            },
            {
                "city": "哈尔滨市",
                "name": "平房区",
                "id": "230108"
            },
            {
                "city": "哈尔滨市",
                "name": "松北区",
                "id": "230109"
            },
            {
                "city": "哈尔滨市",
                "name": "香坊区",
                "id": "230110"
            },
            {
                "city": "哈尔滨市",
                "name": "呼兰区",
                "id": "230111"
            },
            {
                "city": "哈尔滨市",
                "name": "阿城区",
                "id": "230112"
            },
            {
                "city": "哈尔滨市",
                "name": "双城区",
                "id": "230113"
            },
            {
                "city": "哈尔滨市",
                "name": "依兰县",
                "id": "230123"
            },
            {
                "city": "哈尔滨市",
                "name": "方正县",
                "id": "230124"
            },
            {
                "city": "哈尔滨市",
                "name": "宾县",
                "id": "230125"
            },
            {
                "city": "哈尔滨市",
                "name": "巴彦县",
                "id": "230126"
            },
            {
                "city": "哈尔滨市",
                "name": "木兰县",
                "id": "230127"
            },
            {
                "city": "哈尔滨市",
                "name": "通河县",
                "id": "230128"
            },
            {
                "city": "哈尔滨市",
                "name": "延寿县",
                "id": "230129"
            },
            {
                "city": "哈尔滨市",
                "name": "尚志市",
                "id": "230183"
            },
            {
                "city": "哈尔滨市",
                "name": "五常市",
                "id": "230184"
            }
        ],
        "230200": [
            {
                "city": "齐齐哈尔市",
                "name": "市辖区",
                "id": "230201"
            },
            {
                "city": "齐齐哈尔市",
                "name": "龙沙区",
                "id": "230202"
            },
            {
                "city": "齐齐哈尔市",
                "name": "建华区",
                "id": "230203"
            },
            {
                "city": "齐齐哈尔市",
                "name": "铁锋区",
                "id": "230204"
            },
            {
                "city": "齐齐哈尔市",
                "name": "昂昂溪区",
                "id": "230205"
            },
            {
                "city": "齐齐哈尔市",
                "name": "富拉尔基区",
                "id": "230206"
            },
            {
                "city": "齐齐哈尔市",
                "name": "碾子山区",
                "id": "230207"
            },
            {
                "city": "齐齐哈尔市",
                "name": "梅里斯达斡尔族区",
                "id": "230208"
            },
            {
                "city": "齐齐哈尔市",
                "name": "龙江县",
                "id": "230221"
            },
            {
                "city": "齐齐哈尔市",
                "name": "依安县",
                "id": "230223"
            },
            {
                "city": "齐齐哈尔市",
                "name": "泰来县",
                "id": "230224"
            },
            {
                "city": "齐齐哈尔市",
                "name": "甘南县",
                "id": "230225"
            },
            {
                "city": "齐齐哈尔市",
                "name": "富裕县",
                "id": "230227"
            },
            {
                "city": "齐齐哈尔市",
                "name": "克山县",
                "id": "230229"
            },
            {
                "city": "齐齐哈尔市",
                "name": "克东县",
                "id": "230230"
            },
            {
                "city": "齐齐哈尔市",
                "name": "拜泉县",
                "id": "230231"
            },
            {
                "city": "齐齐哈尔市",
                "name": "讷河市",
                "id": "230281"
            }
        ],
        "230300": [
            {
                "city": "鸡西市",
                "name": "市辖区",
                "id": "230301"
            },
            {
                "city": "鸡西市",
                "name": "鸡冠区",
                "id": "230302"
            },
            {
                "city": "鸡西市",
                "name": "恒山区",
                "id": "230303"
            },
            {
                "city": "鸡西市",
                "name": "滴道区",
                "id": "230304"
            },
            {
                "city": "鸡西市",
                "name": "梨树区",
                "id": "230305"
            },
            {
                "city": "鸡西市",
                "name": "城子河区",
                "id": "230306"
            },
            {
                "city": "鸡西市",
                "name": "麻山区",
                "id": "230307"
            },
            {
                "city": "鸡西市",
                "name": "鸡东县",
                "id": "230321"
            },
            {
                "city": "鸡西市",
                "name": "虎林市",
                "id": "230381"
            },
            {
                "city": "鸡西市",
                "name": "密山市",
                "id": "230382"
            }
        ],
        "230400": [
            {
                "city": "鹤岗市",
                "name": "市辖区",
                "id": "230401"
            },
            {
                "city": "鹤岗市",
                "name": "向阳区",
                "id": "230402"
            },
            {
                "city": "鹤岗市",
                "name": "工农区",
                "id": "230403"
            },
            {
                "city": "鹤岗市",
                "name": "南山区",
                "id": "230404"
            },
            {
                "city": "鹤岗市",
                "name": "兴安区",
                "id": "230405"
            },
            {
                "city": "鹤岗市",
                "name": "东山区",
                "id": "230406"
            },
            {
                "city": "鹤岗市",
                "name": "兴山区",
                "id": "230407"
            },
            {
                "city": "鹤岗市",
                "name": "萝北县",
                "id": "230421"
            },
            {
                "city": "鹤岗市",
                "name": "绥滨县",
                "id": "230422"
            }
        ],
        "230500": [
            {
                "city": "双鸭山市",
                "name": "市辖区",
                "id": "230501"
            },
            {
                "city": "双鸭山市",
                "name": "尖山区",
                "id": "230502"
            },
            {
                "city": "双鸭山市",
                "name": "岭东区",
                "id": "230503"
            },
            {
                "city": "双鸭山市",
                "name": "四方台区",
                "id": "230505"
            },
            {
                "city": "双鸭山市",
                "name": "宝山区",
                "id": "230506"
            },
            {
                "city": "双鸭山市",
                "name": "集贤县",
                "id": "230521"
            },
            {
                "city": "双鸭山市",
                "name": "友谊县",
                "id": "230522"
            },
            {
                "city": "双鸭山市",
                "name": "宝清县",
                "id": "230523"
            },
            {
                "city": "双鸭山市",
                "name": "饶河县",
                "id": "230524"
            }
        ],
        "230600": [
            {
                "city": "大庆市",
                "name": "市辖区",
                "id": "230601"
            },
            {
                "city": "大庆市",
                "name": "萨尔图区",
                "id": "230602"
            },
            {
                "city": "大庆市",
                "name": "龙凤区",
                "id": "230603"
            },
            {
                "city": "大庆市",
                "name": "让胡路区",
                "id": "230604"
            },
            {
                "city": "大庆市",
                "name": "红岗区",
                "id": "230605"
            },
            {
                "city": "大庆市",
                "name": "大同区",
                "id": "230606"
            },
            {
                "city": "大庆市",
                "name": "肇州县",
                "id": "230621"
            },
            {
                "city": "大庆市",
                "name": "肇源县",
                "id": "230622"
            },
            {
                "city": "大庆市",
                "name": "林甸县",
                "id": "230623"
            },
            {
                "city": "大庆市",
                "name": "杜尔伯特蒙古族自治县",
                "id": "230624"
            }
        ],
        "230700": [
            {
                "city": "伊春市",
                "name": "市辖区",
                "id": "230701"
            },
            {
                "city": "伊春市",
                "name": "伊春区",
                "id": "230702"
            },
            {
                "city": "伊春市",
                "name": "南岔区",
                "id": "230703"
            },
            {
                "city": "伊春市",
                "name": "友好区",
                "id": "230704"
            },
            {
                "city": "伊春市",
                "name": "西林区",
                "id": "230705"
            },
            {
                "city": "伊春市",
                "name": "翠峦区",
                "id": "230706"
            },
            {
                "city": "伊春市",
                "name": "新青区",
                "id": "230707"
            },
            {
                "city": "伊春市",
                "name": "美溪区",
                "id": "230708"
            },
            {
                "city": "伊春市",
                "name": "金山屯区",
                "id": "230709"
            },
            {
                "city": "伊春市",
                "name": "五营区",
                "id": "230710"
            },
            {
                "city": "伊春市",
                "name": "乌马河区",
                "id": "230711"
            },
            {
                "city": "伊春市",
                "name": "汤旺河区",
                "id": "230712"
            },
            {
                "city": "伊春市",
                "name": "带岭区",
                "id": "230713"
            },
            {
                "city": "伊春市",
                "name": "乌伊岭区",
                "id": "230714"
            },
            {
                "city": "伊春市",
                "name": "红星区",
                "id": "230715"
            },
            {
                "city": "伊春市",
                "name": "上甘岭区",
                "id": "230716"
            },
            {
                "city": "伊春市",
                "name": "嘉荫县",
                "id": "230722"
            },
            {
                "city": "伊春市",
                "name": "铁力市",
                "id": "230781"
            }
        ],
        "230800": [
            {
                "city": "佳木斯市",
                "name": "市辖区",
                "id": "230801"
            },
            {
                "city": "佳木斯市",
                "name": "向阳区",
                "id": "230803"
            },
            {
                "city": "佳木斯市",
                "name": "前进区",
                "id": "230804"
            },
            {
                "city": "佳木斯市",
                "name": "东风区",
                "id": "230805"
            },
            {
                "city": "佳木斯市",
                "name": "郊区",
                "id": "230811"
            },
            {
                "city": "佳木斯市",
                "name": "桦南县",
                "id": "230822"
            },
            {
                "city": "佳木斯市",
                "name": "桦川县",
                "id": "230826"
            },
            {
                "city": "佳木斯市",
                "name": "汤原县",
                "id": "230828"
            },
            {
                "city": "佳木斯市",
                "name": "同江市",
                "id": "230881"
            },
            {
                "city": "佳木斯市",
                "name": "富锦市",
                "id": "230882"
            },
            {
                "city": "佳木斯市",
                "name": "抚远市",
                "id": "230883"
            }
        ],
        "230900": [
            {
                "city": "七台河市",
                "name": "市辖区",
                "id": "230901"
            },
            {
                "city": "七台河市",
                "name": "新兴区",
                "id": "230902"
            },
            {
                "city": "七台河市",
                "name": "桃山区",
                "id": "230903"
            },
            {
                "city": "七台河市",
                "name": "茄子河区",
                "id": "230904"
            },
            {
                "city": "七台河市",
                "name": "勃利县",
                "id": "230921"
            }
        ],
        "231000": [
            {
                "city": "牡丹江市",
                "name": "市辖区",
                "id": "231001"
            },
            {
                "city": "牡丹江市",
                "name": "东安区",
                "id": "231002"
            },
            {
                "city": "牡丹江市",
                "name": "阳明区",
                "id": "231003"
            },
            {
                "city": "牡丹江市",
                "name": "爱民区",
                "id": "231004"
            },
            {
                "city": "牡丹江市",
                "name": "西安区",
                "id": "231005"
            },
            {
                "city": "牡丹江市",
                "name": "林口县",
                "id": "231025"
            },
            {
                "city": "牡丹江市",
                "name": "绥芬河市",
                "id": "231081"
            },
            {
                "city": "牡丹江市",
                "name": "海林市",
                "id": "231083"
            },
            {
                "city": "牡丹江市",
                "name": "宁安市",
                "id": "231084"
            },
            {
                "city": "牡丹江市",
                "name": "穆棱市",
                "id": "231085"
            },
            {
                "city": "牡丹江市",
                "name": "东宁市",
                "id": "231086"
            }
        ],
        "231100": [
            {
                "city": "黑河市",
                "name": "市辖区",
                "id": "231101"
            },
            {
                "city": "黑河市",
                "name": "爱辉区",
                "id": "231102"
            },
            {
                "city": "黑河市",
                "name": "嫩江县",
                "id": "231121"
            },
            {
                "city": "黑河市",
                "name": "逊克县",
                "id": "231123"
            },
            {
                "city": "黑河市",
                "name": "孙吴县",
                "id": "231124"
            },
            {
                "city": "黑河市",
                "name": "北安市",
                "id": "231181"
            },
            {
                "city": "黑河市",
                "name": "五大连池市",
                "id": "231182"
            }
        ],
        "231200": [
            {
                "city": "绥化市",
                "name": "市辖区",
                "id": "231201"
            },
            {
                "city": "绥化市",
                "name": "北林区",
                "id": "231202"
            },
            {
                "city": "绥化市",
                "name": "望奎县",
                "id": "231221"
            },
            {
                "city": "绥化市",
                "name": "兰西县",
                "id": "231222"
            },
            {
                "city": "绥化市",
                "name": "青冈县",
                "id": "231223"
            },
            {
                "city": "绥化市",
                "name": "庆安县",
                "id": "231224"
            },
            {
                "city": "绥化市",
                "name": "明水县",
                "id": "231225"
            },
            {
                "city": "绥化市",
                "name": "绥棱县",
                "id": "231226"
            },
            {
                "city": "绥化市",
                "name": "安达市",
                "id": "231281"
            },
            {
                "city": "绥化市",
                "name": "肇东市",
                "id": "231282"
            },
            {
                "city": "绥化市",
                "name": "海伦市",
                "id": "231283"
            }
        ],
        "232700": [
            {
                "city": "大兴安岭地区",
                "name": "呼玛县",
                "id": "232721"
            },
            {
                "city": "大兴安岭地区",
                "name": "塔河县",
                "id": "232722"
            },
            {
                "city": "大兴安岭地区",
                "name": "漠河县",
                "id": "232723"
            }
        ],
        "310100": [
            {
                "city": "市辖区",
                "name": "黄浦区",
                "id": "310101"
            },
            {
                "city": "市辖区",
                "name": "徐汇区",
                "id": "310104"
            },
            {
                "city": "市辖区",
                "name": "长宁区",
                "id": "310105"
            },
            {
                "city": "市辖区",
                "name": "静安区",
                "id": "310106"
            },
            {
                "city": "市辖区",
                "name": "普陀区",
                "id": "310107"
            },
            {
                "city": "市辖区",
                "name": "虹口区",
                "id": "310109"
            },
            {
                "city": "市辖区",
                "name": "杨浦区",
                "id": "310110"
            },
            {
                "city": "市辖区",
                "name": "闵行区",
                "id": "310112"
            },
            {
                "city": "市辖区",
                "name": "宝山区",
                "id": "310113"
            },
            {
                "city": "市辖区",
                "name": "嘉定区",
                "id": "310114"
            },
            {
                "city": "市辖区",
                "name": "浦东新区",
                "id": "310115"
            },
            {
                "city": "市辖区",
                "name": "金山区",
                "id": "310116"
            },
            {
                "city": "市辖区",
                "name": "松江区",
                "id": "310117"
            },
            {
                "city": "市辖区",
                "name": "青浦区",
                "id": "310118"
            },
            {
                "city": "市辖区",
                "name": "奉贤区",
                "id": "310120"
            },
            {
                "city": "市辖区",
                "name": "崇明区",
                "id": "310151"
            }
        ],
        "320100": [
            {
                "city": "南京市",
                "name": "市辖区",
                "id": "320101"
            },
            {
                "city": "南京市",
                "name": "玄武区",
                "id": "320102"
            },
            {
                "city": "南京市",
                "name": "秦淮区",
                "id": "320104"
            },
            {
                "city": "南京市",
                "name": "建邺区",
                "id": "320105"
            },
            {
                "city": "南京市",
                "name": "鼓楼区",
                "id": "320106"
            },
            {
                "city": "南京市",
                "name": "浦口区",
                "id": "320111"
            },
            {
                "city": "南京市",
                "name": "栖霞区",
                "id": "320113"
            },
            {
                "city": "南京市",
                "name": "雨花台区",
                "id": "320114"
            },
            {
                "city": "南京市",
                "name": "江宁区",
                "id": "320115"
            },
            {
                "city": "南京市",
                "name": "六合区",
                "id": "320116"
            },
            {
                "city": "南京市",
                "name": "溧水区",
                "id": "320117"
            },
            {
                "city": "南京市",
                "name": "高淳区",
                "id": "320118"
            }
        ],
        "320200": [
            {
                "city": "无锡市",
                "name": "市辖区",
                "id": "320201"
            },
            {
                "city": "无锡市",
                "name": "锡山区",
                "id": "320205"
            },
            {
                "city": "无锡市",
                "name": "惠山区",
                "id": "320206"
            },
            {
                "city": "无锡市",
                "name": "滨湖区",
                "id": "320211"
            },
            {
                "city": "无锡市",
                "name": "梁溪区",
                "id": "320213"
            },
            {
                "city": "无锡市",
                "name": "新吴区",
                "id": "320214"
            },
            {
                "city": "无锡市",
                "name": "江阴市",
                "id": "320281"
            },
            {
                "city": "无锡市",
                "name": "宜兴市",
                "id": "320282"
            }
        ],
        "320300": [
            {
                "city": "徐州市",
                "name": "市辖区",
                "id": "320301"
            },
            {
                "city": "徐州市",
                "name": "鼓楼区",
                "id": "320302"
            },
            {
                "city": "徐州市",
                "name": "云龙区",
                "id": "320303"
            },
            {
                "city": "徐州市",
                "name": "贾汪区",
                "id": "320305"
            },
            {
                "city": "徐州市",
                "name": "泉山区",
                "id": "320311"
            },
            {
                "city": "徐州市",
                "name": "铜山区",
                "id": "320312"
            },
            {
                "city": "徐州市",
                "name": "丰县",
                "id": "320321"
            },
            {
                "city": "徐州市",
                "name": "沛县",
                "id": "320322"
            },
            {
                "city": "徐州市",
                "name": "睢宁县",
                "id": "320324"
            },
            {
                "city": "徐州市",
                "name": "新沂市",
                "id": "320381"
            },
            {
                "city": "徐州市",
                "name": "邳州市",
                "id": "320382"
            }
        ],
        "320400": [
            {
                "city": "常州市",
                "name": "市辖区",
                "id": "320401"
            },
            {
                "city": "常州市",
                "name": "天宁区",
                "id": "320402"
            },
            {
                "city": "常州市",
                "name": "钟楼区",
                "id": "320404"
            },
            {
                "city": "常州市",
                "name": "新北区",
                "id": "320411"
            },
            {
                "city": "常州市",
                "name": "武进区",
                "id": "320412"
            },
            {
                "city": "常州市",
                "name": "金坛区",
                "id": "320413"
            },
            {
                "city": "常州市",
                "name": "溧阳市",
                "id": "320481"
            }
        ],
        "320500": [
            {
                "city": "苏州市",
                "name": "市辖区",
                "id": "320501"
            },
            {
                "city": "苏州市",
                "name": "虎丘区",
                "id": "320505"
            },
            {
                "city": "苏州市",
                "name": "吴中区",
                "id": "320506"
            },
            {
                "city": "苏州市",
                "name": "相城区",
                "id": "320507"
            },
            {
                "city": "苏州市",
                "name": "姑苏区",
                "id": "320508"
            },
            {
                "city": "苏州市",
                "name": "吴江区",
                "id": "320509"
            },
            {
                "city": "苏州市",
                "name": "常熟市",
                "id": "320581"
            },
            {
                "city": "苏州市",
                "name": "张家港市",
                "id": "320582"
            },
            {
                "city": "苏州市",
                "name": "昆山市",
                "id": "320583"
            },
            {
                "city": "苏州市",
                "name": "太仓市",
                "id": "320585"
            }
        ],
        "320600": [
            {
                "city": "南通市",
                "name": "市辖区",
                "id": "320601"
            },
            {
                "city": "南通市",
                "name": "崇川区",
                "id": "320602"
            },
            {
                "city": "南通市",
                "name": "港闸区",
                "id": "320611"
            },
            {
                "city": "南通市",
                "name": "通州区",
                "id": "320612"
            },
            {
                "city": "南通市",
                "name": "海安县",
                "id": "320621"
            },
            {
                "city": "南通市",
                "name": "如东县",
                "id": "320623"
            },
            {
                "city": "南通市",
                "name": "启东市",
                "id": "320681"
            },
            {
                "city": "南通市",
                "name": "如皋市",
                "id": "320682"
            },
            {
                "city": "南通市",
                "name": "海门市",
                "id": "320684"
            }
        ],
        "320700": [
            {
                "city": "连云港市",
                "name": "市辖区",
                "id": "320701"
            },
            {
                "city": "连云港市",
                "name": "连云区",
                "id": "320703"
            },
            {
                "city": "连云港市",
                "name": "海州区",
                "id": "320706"
            },
            {
                "city": "连云港市",
                "name": "赣榆区",
                "id": "320707"
            },
            {
                "city": "连云港市",
                "name": "东海县",
                "id": "320722"
            },
            {
                "city": "连云港市",
                "name": "灌云县",
                "id": "320723"
            },
            {
                "city": "连云港市",
                "name": "灌南县",
                "id": "320724"
            }
        ],
        "320800": [
            {
                "city": "淮安市",
                "name": "市辖区",
                "id": "320801"
            },
            {
                "city": "淮安市",
                "name": "淮安区",
                "id": "320803"
            },
            {
                "city": "淮安市",
                "name": "淮阴区",
                "id": "320804"
            },
            {
                "city": "淮安市",
                "name": "清江浦区",
                "id": "320812"
            },
            {
                "city": "淮安市",
                "name": "洪泽区",
                "id": "320813"
            },
            {
                "city": "淮安市",
                "name": "涟水县",
                "id": "320826"
            },
            {
                "city": "淮安市",
                "name": "盱眙县",
                "id": "320830"
            },
            {
                "city": "淮安市",
                "name": "金湖县",
                "id": "320831"
            }
        ],
        "320900": [
            {
                "city": "盐城市",
                "name": "市辖区",
                "id": "320901"
            },
            {
                "city": "盐城市",
                "name": "亭湖区",
                "id": "320902"
            },
            {
                "city": "盐城市",
                "name": "盐都区",
                "id": "320903"
            },
            {
                "city": "盐城市",
                "name": "大丰区",
                "id": "320904"
            },
            {
                "city": "盐城市",
                "name": "响水县",
                "id": "320921"
            },
            {
                "city": "盐城市",
                "name": "滨海县",
                "id": "320922"
            },
            {
                "city": "盐城市",
                "name": "阜宁县",
                "id": "320923"
            },
            {
                "city": "盐城市",
                "name": "射阳县",
                "id": "320924"
            },
            {
                "city": "盐城市",
                "name": "建湖县",
                "id": "320925"
            },
            {
                "city": "盐城市",
                "name": "东台市",
                "id": "320981"
            }
        ],
        "321000": [
            {
                "city": "扬州市",
                "name": "市辖区",
                "id": "321001"
            },
            {
                "city": "扬州市",
                "name": "广陵区",
                "id": "321002"
            },
            {
                "city": "扬州市",
                "name": "邗江区",
                "id": "321003"
            },
            {
                "city": "扬州市",
                "name": "江都区",
                "id": "321012"
            },
            {
                "city": "扬州市",
                "name": "宝应县",
                "id": "321023"
            },
            {
                "city": "扬州市",
                "name": "仪征市",
                "id": "321081"
            },
            {
                "city": "扬州市",
                "name": "高邮市",
                "id": "321084"
            }
        ],
        "321100": [
            {
                "city": "镇江市",
                "name": "市辖区",
                "id": "321101"
            },
            {
                "city": "镇江市",
                "name": "京口区",
                "id": "321102"
            },
            {
                "city": "镇江市",
                "name": "润州区",
                "id": "321111"
            },
            {
                "city": "镇江市",
                "name": "丹徒区",
                "id": "321112"
            },
            {
                "city": "镇江市",
                "name": "丹阳市",
                "id": "321181"
            },
            {
                "city": "镇江市",
                "name": "扬中市",
                "id": "321182"
            },
            {
                "city": "镇江市",
                "name": "句容市",
                "id": "321183"
            }
        ],
        "321200": [
            {
                "city": "泰州市",
                "name": "市辖区",
                "id": "321201"
            },
            {
                "city": "泰州市",
                "name": "海陵区",
                "id": "321202"
            },
            {
                "city": "泰州市",
                "name": "高港区",
                "id": "321203"
            },
            {
                "city": "泰州市",
                "name": "姜堰区",
                "id": "321204"
            },
            {
                "city": "泰州市",
                "name": "兴化市",
                "id": "321281"
            },
            {
                "city": "泰州市",
                "name": "靖江市",
                "id": "321282"
            },
            {
                "city": "泰州市",
                "name": "泰兴市",
                "id": "321283"
            }
        ],
        "321300": [
            {
                "city": "宿迁市",
                "name": "市辖区",
                "id": "321301"
            },
            {
                "city": "宿迁市",
                "name": "宿城区",
                "id": "321302"
            },
            {
                "city": "宿迁市",
                "name": "宿豫区",
                "id": "321311"
            },
            {
                "city": "宿迁市",
                "name": "沭阳县",
                "id": "321322"
            },
            {
                "city": "宿迁市",
                "name": "泗阳县",
                "id": "321323"
            },
            {
                "city": "宿迁市",
                "name": "泗洪县",
                "id": "321324"
            }
        ],
        "330100": [
            {
                "city": "杭州市",
                "name": "市辖区",
                "id": "330101"
            },
            {
                "city": "杭州市",
                "name": "上城区",
                "id": "330102"
            },
            {
                "city": "杭州市",
                "name": "下城区",
                "id": "330103"
            },
            {
                "city": "杭州市",
                "name": "江干区",
                "id": "330104"
            },
            {
                "city": "杭州市",
                "name": "拱墅区",
                "id": "330105"
            },
            {
                "city": "杭州市",
                "name": "西湖区",
                "id": "330106"
            },
            {
                "city": "杭州市",
                "name": "滨江区",
                "id": "330108"
            },
            {
                "city": "杭州市",
                "name": "萧山区",
                "id": "330109"
            },
            {
                "city": "杭州市",
                "name": "余杭区",
                "id": "330110"
            },
            {
                "city": "杭州市",
                "name": "富阳区",
                "id": "330111"
            },
            {
                "city": "杭州市",
                "name": "桐庐县",
                "id": "330122"
            },
            {
                "city": "杭州市",
                "name": "淳安县",
                "id": "330127"
            },
            {
                "city": "杭州市",
                "name": "建德市",
                "id": "330182"
            },
            {
                "city": "杭州市",
                "name": "临安市",
                "id": "330185"
            }
        ],
        "330200": [
            {
                "city": "宁波市",
                "name": "市辖区",
                "id": "330201"
            },
            {
                "city": "宁波市",
                "name": "海曙区",
                "id": "330203"
            },
            {
                "city": "宁波市",
                "name": "江东区",
                "id": "330204"
            },
            {
                "city": "宁波市",
                "name": "江北区",
                "id": "330205"
            },
            {
                "city": "宁波市",
                "name": "北仑区",
                "id": "330206"
            },
            {
                "city": "宁波市",
                "name": "镇海区",
                "id": "330211"
            },
            {
                "city": "宁波市",
                "name": "鄞州区",
                "id": "330212"
            },
            {
                "city": "宁波市",
                "name": "象山县",
                "id": "330225"
            },
            {
                "city": "宁波市",
                "name": "宁海县",
                "id": "330226"
            },
            {
                "city": "宁波市",
                "name": "余姚市",
                "id": "330281"
            },
            {
                "city": "宁波市",
                "name": "慈溪市",
                "id": "330282"
            },
            {
                "city": "宁波市",
                "name": "奉化市",
                "id": "330283"
            }
        ],
        "330300": [
            {
                "city": "温州市",
                "name": "市辖区",
                "id": "330301"
            },
            {
                "city": "温州市",
                "name": "鹿城区",
                "id": "330302"
            },
            {
                "city": "温州市",
                "name": "龙湾区",
                "id": "330303"
            },
            {
                "city": "温州市",
                "name": "瓯海区",
                "id": "330304"
            },
            {
                "city": "温州市",
                "name": "洞头区",
                "id": "330305"
            },
            {
                "city": "温州市",
                "name": "永嘉县",
                "id": "330324"
            },
            {
                "city": "温州市",
                "name": "平阳县",
                "id": "330326"
            },
            {
                "city": "温州市",
                "name": "苍南县",
                "id": "330327"
            },
            {
                "city": "温州市",
                "name": "文成县",
                "id": "330328"
            },
            {
                "city": "温州市",
                "name": "泰顺县",
                "id": "330329"
            },
            {
                "city": "温州市",
                "name": "瑞安市",
                "id": "330381"
            },
            {
                "city": "温州市",
                "name": "乐清市",
                "id": "330382"
            }
        ],
        "330400": [
            {
                "city": "嘉兴市",
                "name": "市辖区",
                "id": "330401"
            },
            {
                "city": "嘉兴市",
                "name": "南湖区",
                "id": "330402"
            },
            {
                "city": "嘉兴市",
                "name": "秀洲区",
                "id": "330411"
            },
            {
                "city": "嘉兴市",
                "name": "嘉善县",
                "id": "330421"
            },
            {
                "city": "嘉兴市",
                "name": "海盐县",
                "id": "330424"
            },
            {
                "city": "嘉兴市",
                "name": "海宁市",
                "id": "330481"
            },
            {
                "city": "嘉兴市",
                "name": "平湖市",
                "id": "330482"
            },
            {
                "city": "嘉兴市",
                "name": "桐乡市",
                "id": "330483"
            }
        ],
        "330500": [
            {
                "city": "湖州市",
                "name": "市辖区",
                "id": "330501"
            },
            {
                "city": "湖州市",
                "name": "吴兴区",
                "id": "330502"
            },
            {
                "city": "湖州市",
                "name": "南浔区",
                "id": "330503"
            },
            {
                "city": "湖州市",
                "name": "德清县",
                "id": "330521"
            },
            {
                "city": "湖州市",
                "name": "长兴县",
                "id": "330522"
            },
            {
                "city": "湖州市",
                "name": "安吉县",
                "id": "330523"
            }
        ],
        "330600": [
            {
                "city": "绍兴市",
                "name": "市辖区",
                "id": "330601"
            },
            {
                "city": "绍兴市",
                "name": "越城区",
                "id": "330602"
            },
            {
                "city": "绍兴市",
                "name": "柯桥区",
                "id": "330603"
            },
            {
                "city": "绍兴市",
                "name": "上虞区",
                "id": "330604"
            },
            {
                "city": "绍兴市",
                "name": "新昌县",
                "id": "330624"
            },
            {
                "city": "绍兴市",
                "name": "诸暨市",
                "id": "330681"
            },
            {
                "city": "绍兴市",
                "name": "嵊州市",
                "id": "330683"
            }
        ],
        "330700": [
            {
                "city": "金华市",
                "name": "市辖区",
                "id": "330701"
            },
            {
                "city": "金华市",
                "name": "婺城区",
                "id": "330702"
            },
            {
                "city": "金华市",
                "name": "金东区",
                "id": "330703"
            },
            {
                "city": "金华市",
                "name": "武义县",
                "id": "330723"
            },
            {
                "city": "金华市",
                "name": "浦江县",
                "id": "330726"
            },
            {
                "city": "金华市",
                "name": "磐安县",
                "id": "330727"
            },
            {
                "city": "金华市",
                "name": "兰溪市",
                "id": "330781"
            },
            {
                "city": "金华市",
                "name": "义乌市",
                "id": "330782"
            },
            {
                "city": "金华市",
                "name": "东阳市",
                "id": "330783"
            },
            {
                "city": "金华市",
                "name": "永康市",
                "id": "330784"
            }
        ],
        "330800": [
            {
                "city": "衢州市",
                "name": "市辖区",
                "id": "330801"
            },
            {
                "city": "衢州市",
                "name": "柯城区",
                "id": "330802"
            },
            {
                "city": "衢州市",
                "name": "衢江区",
                "id": "330803"
            },
            {
                "city": "衢州市",
                "name": "常山县",
                "id": "330822"
            },
            {
                "city": "衢州市",
                "name": "开化县",
                "id": "330824"
            },
            {
                "city": "衢州市",
                "name": "龙游县",
                "id": "330825"
            },
            {
                "city": "衢州市",
                "name": "江山市",
                "id": "330881"
            }
        ],
        "330900": [
            {
                "city": "舟山市",
                "name": "市辖区",
                "id": "330901"
            },
            {
                "city": "舟山市",
                "name": "定海区",
                "id": "330902"
            },
            {
                "city": "舟山市",
                "name": "普陀区",
                "id": "330903"
            },
            {
                "city": "舟山市",
                "name": "岱山县",
                "id": "330921"
            },
            {
                "city": "舟山市",
                "name": "嵊泗县",
                "id": "330922"
            }
        ],
        "331000": [
            {
                "city": "台州市",
                "name": "市辖区",
                "id": "331001"
            },
            {
                "city": "台州市",
                "name": "椒江区",
                "id": "331002"
            },
            {
                "city": "台州市",
                "name": "黄岩区",
                "id": "331003"
            },
            {
                "city": "台州市",
                "name": "路桥区",
                "id": "331004"
            },
            {
                "city": "台州市",
                "name": "玉环县",
                "id": "331021"
            },
            {
                "city": "台州市",
                "name": "三门县",
                "id": "331022"
            },
            {
                "city": "台州市",
                "name": "天台县",
                "id": "331023"
            },
            {
                "city": "台州市",
                "name": "仙居县",
                "id": "331024"
            },
            {
                "city": "台州市",
                "name": "温岭市",
                "id": "331081"
            },
            {
                "city": "台州市",
                "name": "临海市",
                "id": "331082"
            }
        ],
        "331100": [
            {
                "city": "丽水市",
                "name": "市辖区",
                "id": "331101"
            },
            {
                "city": "丽水市",
                "name": "莲都区",
                "id": "331102"
            },
            {
                "city": "丽水市",
                "name": "青田县",
                "id": "331121"
            },
            {
                "city": "丽水市",
                "name": "缙云县",
                "id": "331122"
            },
            {
                "city": "丽水市",
                "name": "遂昌县",
                "id": "331123"
            },
            {
                "city": "丽水市",
                "name": "松阳县",
                "id": "331124"
            },
            {
                "city": "丽水市",
                "name": "云和县",
                "id": "331125"
            },
            {
                "city": "丽水市",
                "name": "庆元县",
                "id": "331126"
            },
            {
                "city": "丽水市",
                "name": "景宁畲族自治县",
                "id": "331127"
            },
            {
                "city": "丽水市",
                "name": "龙泉市",
                "id": "331181"
            }
        ],
        "340100": [
            {
                "city": "合肥市",
                "name": "市辖区",
                "id": "340101"
            },
            {
                "city": "合肥市",
                "name": "瑶海区",
                "id": "340102"
            },
            {
                "city": "合肥市",
                "name": "庐阳区",
                "id": "340103"
            },
            {
                "city": "合肥市",
                "name": "蜀山区",
                "id": "340104"
            },
            {
                "city": "合肥市",
                "name": "包河区",
                "id": "340111"
            },
            {
                "city": "合肥市",
                "name": "长丰县",
                "id": "340121"
            },
            {
                "city": "合肥市",
                "name": "肥东县",
                "id": "340122"
            },
            {
                "city": "合肥市",
                "name": "肥西县",
                "id": "340123"
            },
            {
                "city": "合肥市",
                "name": "庐江县",
                "id": "340124"
            },
            {
                "city": "合肥市",
                "name": "巢湖市",
                "id": "340181"
            }
        ],
        "340200": [
            {
                "city": "芜湖市",
                "name": "市辖区",
                "id": "340201"
            },
            {
                "city": "芜湖市",
                "name": "镜湖区",
                "id": "340202"
            },
            {
                "city": "芜湖市",
                "name": "弋江区",
                "id": "340203"
            },
            {
                "city": "芜湖市",
                "name": "鸠江区",
                "id": "340207"
            },
            {
                "city": "芜湖市",
                "name": "三山区",
                "id": "340208"
            },
            {
                "city": "芜湖市",
                "name": "芜湖县",
                "id": "340221"
            },
            {
                "city": "芜湖市",
                "name": "繁昌县",
                "id": "340222"
            },
            {
                "city": "芜湖市",
                "name": "南陵县",
                "id": "340223"
            },
            {
                "city": "芜湖市",
                "name": "无为县",
                "id": "340225"
            }
        ],
        "340300": [
            {
                "city": "蚌埠市",
                "name": "市辖区",
                "id": "340301"
            },
            {
                "city": "蚌埠市",
                "name": "龙子湖区",
                "id": "340302"
            },
            {
                "city": "蚌埠市",
                "name": "蚌山区",
                "id": "340303"
            },
            {
                "city": "蚌埠市",
                "name": "禹会区",
                "id": "340304"
            },
            {
                "city": "蚌埠市",
                "name": "淮上区",
                "id": "340311"
            },
            {
                "city": "蚌埠市",
                "name": "怀远县",
                "id": "340321"
            },
            {
                "city": "蚌埠市",
                "name": "五河县",
                "id": "340322"
            },
            {
                "city": "蚌埠市",
                "name": "固镇县",
                "id": "340323"
            }
        ],
        "340400": [
            {
                "city": "淮南市",
                "name": "市辖区",
                "id": "340401"
            },
            {
                "city": "淮南市",
                "name": "大通区",
                "id": "340402"
            },
            {
                "city": "淮南市",
                "name": "田家庵区",
                "id": "340403"
            },
            {
                "city": "淮南市",
                "name": "谢家集区",
                "id": "340404"
            },
            {
                "city": "淮南市",
                "name": "八公山区",
                "id": "340405"
            },
            {
                "city": "淮南市",
                "name": "潘集区",
                "id": "340406"
            },
            {
                "city": "淮南市",
                "name": "凤台县",
                "id": "340421"
            },
            {
                "city": "淮南市",
                "name": "寿县",
                "id": "340422"
            }
        ],
        "340500": [
            {
                "city": "马鞍山市",
                "name": "市辖区",
                "id": "340501"
            },
            {
                "city": "马鞍山市",
                "name": "花山区",
                "id": "340503"
            },
            {
                "city": "马鞍山市",
                "name": "雨山区",
                "id": "340504"
            },
            {
                "city": "马鞍山市",
                "name": "博望区",
                "id": "340506"
            },
            {
                "city": "马鞍山市",
                "name": "当涂县",
                "id": "340521"
            },
            {
                "city": "马鞍山市",
                "name": "含山县",
                "id": "340522"
            },
            {
                "city": "马鞍山市",
                "name": "和县",
                "id": "340523"
            }
        ],
        "340600": [
            {
                "city": "淮北市",
                "name": "市辖区",
                "id": "340601"
            },
            {
                "city": "淮北市",
                "name": "杜集区",
                "id": "340602"
            },
            {
                "city": "淮北市",
                "name": "相山区",
                "id": "340603"
            },
            {
                "city": "淮北市",
                "name": "烈山区",
                "id": "340604"
            },
            {
                "city": "淮北市",
                "name": "濉溪县",
                "id": "340621"
            }
        ],
        "340700": [
            {
                "city": "铜陵市",
                "name": "市辖区",
                "id": "340701"
            },
            {
                "city": "铜陵市",
                "name": "铜官区",
                "id": "340705"
            },
            {
                "city": "铜陵市",
                "name": "义安区",
                "id": "340706"
            },
            {
                "city": "铜陵市",
                "name": "郊区",
                "id": "340711"
            },
            {
                "city": "铜陵市",
                "name": "枞阳县",
                "id": "340722"
            }
        ],
        "340800": [
            {
                "city": "安庆市",
                "name": "市辖区",
                "id": "340801"
            },
            {
                "city": "安庆市",
                "name": "迎江区",
                "id": "340802"
            },
            {
                "city": "安庆市",
                "name": "大观区",
                "id": "340803"
            },
            {
                "city": "安庆市",
                "name": "宜秀区",
                "id": "340811"
            },
            {
                "city": "安庆市",
                "name": "怀宁县",
                "id": "340822"
            },
            {
                "city": "安庆市",
                "name": "潜山县",
                "id": "340824"
            },
            {
                "city": "安庆市",
                "name": "太湖县",
                "id": "340825"
            },
            {
                "city": "安庆市",
                "name": "宿松县",
                "id": "340826"
            },
            {
                "city": "安庆市",
                "name": "望江县",
                "id": "340827"
            },
            {
                "city": "安庆市",
                "name": "岳西县",
                "id": "340828"
            },
            {
                "city": "安庆市",
                "name": "桐城市",
                "id": "340881"
            }
        ],
        "341000": [
            {
                "city": "黄山市",
                "name": "市辖区",
                "id": "341001"
            },
            {
                "city": "黄山市",
                "name": "屯溪区",
                "id": "341002"
            },
            {
                "city": "黄山市",
                "name": "黄山区",
                "id": "341003"
            },
            {
                "city": "黄山市",
                "name": "徽州区",
                "id": "341004"
            },
            {
                "city": "黄山市",
                "name": "歙县",
                "id": "341021"
            },
            {
                "city": "黄山市",
                "name": "休宁县",
                "id": "341022"
            },
            {
                "city": "黄山市",
                "name": "黟县",
                "id": "341023"
            },
            {
                "city": "黄山市",
                "name": "祁门县",
                "id": "341024"
            }
        ],
        "341100": [
            {
                "city": "滁州市",
                "name": "市辖区",
                "id": "341101"
            },
            {
                "city": "滁州市",
                "name": "琅琊区",
                "id": "341102"
            },
            {
                "city": "滁州市",
                "name": "南谯区",
                "id": "341103"
            },
            {
                "city": "滁州市",
                "name": "来安县",
                "id": "341122"
            },
            {
                "city": "滁州市",
                "name": "全椒县",
                "id": "341124"
            },
            {
                "city": "滁州市",
                "name": "定远县",
                "id": "341125"
            },
            {
                "city": "滁州市",
                "name": "凤阳县",
                "id": "341126"
            },
            {
                "city": "滁州市",
                "name": "天长市",
                "id": "341181"
            },
            {
                "city": "滁州市",
                "name": "明光市",
                "id": "341182"
            }
        ],
        "341200": [
            {
                "city": "阜阳市",
                "name": "市辖区",
                "id": "341201"
            },
            {
                "city": "阜阳市",
                "name": "颍州区",
                "id": "341202"
            },
            {
                "city": "阜阳市",
                "name": "颍东区",
                "id": "341203"
            },
            {
                "city": "阜阳市",
                "name": "颍泉区",
                "id": "341204"
            },
            {
                "city": "阜阳市",
                "name": "临泉县",
                "id": "341221"
            },
            {
                "city": "阜阳市",
                "name": "太和县",
                "id": "341222"
            },
            {
                "city": "阜阳市",
                "name": "阜南县",
                "id": "341225"
            },
            {
                "city": "阜阳市",
                "name": "颍上县",
                "id": "341226"
            },
            {
                "city": "阜阳市",
                "name": "界首市",
                "id": "341282"
            }
        ],
        "341300": [
            {
                "city": "宿州市",
                "name": "市辖区",
                "id": "341301"
            },
            {
                "city": "宿州市",
                "name": "埇桥区",
                "id": "341302"
            },
            {
                "city": "宿州市",
                "name": "砀山县",
                "id": "341321"
            },
            {
                "city": "宿州市",
                "name": "萧县",
                "id": "341322"
            },
            {
                "city": "宿州市",
                "name": "灵璧县",
                "id": "341323"
            },
            {
                "city": "宿州市",
                "name": "泗县",
                "id": "341324"
            }
        ],
        "341500": [
            {
                "city": "六安市",
                "name": "市辖区",
                "id": "341501"
            },
            {
                "city": "六安市",
                "name": "金安区",
                "id": "341502"
            },
            {
                "city": "六安市",
                "name": "裕安区",
                "id": "341503"
            },
            {
                "city": "六安市",
                "name": "叶集区",
                "id": "341504"
            },
            {
                "city": "六安市",
                "name": "霍邱县",
                "id": "341522"
            },
            {
                "city": "六安市",
                "name": "舒城县",
                "id": "341523"
            },
            {
                "city": "六安市",
                "name": "金寨县",
                "id": "341524"
            },
            {
                "city": "六安市",
                "name": "霍山县",
                "id": "341525"
            }
        ],
        "341600": [
            {
                "city": "亳州市",
                "name": "市辖区",
                "id": "341601"
            },
            {
                "city": "亳州市",
                "name": "谯城区",
                "id": "341602"
            },
            {
                "city": "亳州市",
                "name": "涡阳县",
                "id": "341621"
            },
            {
                "city": "亳州市",
                "name": "蒙城县",
                "id": "341622"
            },
            {
                "city": "亳州市",
                "name": "利辛县",
                "id": "341623"
            }
        ],
        "341700": [
            {
                "city": "池州市",
                "name": "市辖区",
                "id": "341701"
            },
            {
                "city": "池州市",
                "name": "贵池区",
                "id": "341702"
            },
            {
                "city": "池州市",
                "name": "东至县",
                "id": "341721"
            },
            {
                "city": "池州市",
                "name": "石台县",
                "id": "341722"
            },
            {
                "city": "池州市",
                "name": "青阳县",
                "id": "341723"
            }
        ],
        "341800": [
            {
                "city": "宣城市",
                "name": "市辖区",
                "id": "341801"
            },
            {
                "city": "宣城市",
                "name": "宣州区",
                "id": "341802"
            },
            {
                "city": "宣城市",
                "name": "郎溪县",
                "id": "341821"
            },
            {
                "city": "宣城市",
                "name": "广德县",
                "id": "341822"
            },
            {
                "city": "宣城市",
                "name": "泾县",
                "id": "341823"
            },
            {
                "city": "宣城市",
                "name": "绩溪县",
                "id": "341824"
            },
            {
                "city": "宣城市",
                "name": "旌德县",
                "id": "341825"
            },
            {
                "city": "宣城市",
                "name": "宁国市",
                "id": "341881"
            }
        ],
        "350100": [
            {
                "city": "福州市",
                "name": "市辖区",
                "id": "350101"
            },
            {
                "city": "福州市",
                "name": "鼓楼区",
                "id": "350102"
            },
            {
                "city": "福州市",
                "name": "台江区",
                "id": "350103"
            },
            {
                "city": "福州市",
                "name": "仓山区",
                "id": "350104"
            },
            {
                "city": "福州市",
                "name": "马尾区",
                "id": "350105"
            },
            {
                "city": "福州市",
                "name": "晋安区",
                "id": "350111"
            },
            {
                "city": "福州市",
                "name": "闽侯县",
                "id": "350121"
            },
            {
                "city": "福州市",
                "name": "连江县",
                "id": "350122"
            },
            {
                "city": "福州市",
                "name": "罗源县",
                "id": "350123"
            },
            {
                "city": "福州市",
                "name": "闽清县",
                "id": "350124"
            },
            {
                "city": "福州市",
                "name": "永泰县",
                "id": "350125"
            },
            {
                "city": "福州市",
                "name": "平潭县",
                "id": "350128"
            },
            {
                "city": "福州市",
                "name": "福清市",
                "id": "350181"
            },
            {
                "city": "福州市",
                "name": "长乐市",
                "id": "350182"
            }
        ],
        "350200": [
            {
                "city": "厦门市",
                "name": "市辖区",
                "id": "350201"
            },
            {
                "city": "厦门市",
                "name": "思明区",
                "id": "350203"
            },
            {
                "city": "厦门市",
                "name": "海沧区",
                "id": "350205"
            },
            {
                "city": "厦门市",
                "name": "湖里区",
                "id": "350206"
            },
            {
                "city": "厦门市",
                "name": "集美区",
                "id": "350211"
            },
            {
                "city": "厦门市",
                "name": "同安区",
                "id": "350212"
            },
            {
                "city": "厦门市",
                "name": "翔安区",
                "id": "350213"
            }
        ],
        "350300": [
            {
                "city": "莆田市",
                "name": "市辖区",
                "id": "350301"
            },
            {
                "city": "莆田市",
                "name": "城厢区",
                "id": "350302"
            },
            {
                "city": "莆田市",
                "name": "涵江区",
                "id": "350303"
            },
            {
                "city": "莆田市",
                "name": "荔城区",
                "id": "350304"
            },
            {
                "city": "莆田市",
                "name": "秀屿区",
                "id": "350305"
            },
            {
                "city": "莆田市",
                "name": "仙游县",
                "id": "350322"
            }
        ],
        "350400": [
            {
                "city": "三明市",
                "name": "市辖区",
                "id": "350401"
            },
            {
                "city": "三明市",
                "name": "梅列区",
                "id": "350402"
            },
            {
                "city": "三明市",
                "name": "三元区",
                "id": "350403"
            },
            {
                "city": "三明市",
                "name": "明溪县",
                "id": "350421"
            },
            {
                "city": "三明市",
                "name": "清流县",
                "id": "350423"
            },
            {
                "city": "三明市",
                "name": "宁化县",
                "id": "350424"
            },
            {
                "city": "三明市",
                "name": "大田县",
                "id": "350425"
            },
            {
                "city": "三明市",
                "name": "尤溪县",
                "id": "350426"
            },
            {
                "city": "三明市",
                "name": "沙县",
                "id": "350427"
            },
            {
                "city": "三明市",
                "name": "将乐县",
                "id": "350428"
            },
            {
                "city": "三明市",
                "name": "泰宁县",
                "id": "350429"
            },
            {
                "city": "三明市",
                "name": "建宁县",
                "id": "350430"
            },
            {
                "city": "三明市",
                "name": "永安市",
                "id": "350481"
            }
        ],
        "350500": [
            {
                "city": "泉州市",
                "name": "市辖区",
                "id": "350501"
            },
            {
                "city": "泉州市",
                "name": "鲤城区",
                "id": "350502"
            },
            {
                "city": "泉州市",
                "name": "丰泽区",
                "id": "350503"
            },
            {
                "city": "泉州市",
                "name": "洛江区",
                "id": "350504"
            },
            {
                "city": "泉州市",
                "name": "泉港区",
                "id": "350505"
            },
            {
                "city": "泉州市",
                "name": "惠安县",
                "id": "350521"
            },
            {
                "city": "泉州市",
                "name": "安溪县",
                "id": "350524"
            },
            {
                "city": "泉州市",
                "name": "永春县",
                "id": "350525"
            },
            {
                "city": "泉州市",
                "name": "德化县",
                "id": "350526"
            },
            {
                "city": "泉州市",
                "name": "金门县",
                "id": "350527"
            },
            {
                "city": "泉州市",
                "name": "石狮市",
                "id": "350581"
            },
            {
                "city": "泉州市",
                "name": "晋江市",
                "id": "350582"
            },
            {
                "city": "泉州市",
                "name": "南安市",
                "id": "350583"
            }
        ],
        "350600": [
            {
                "city": "漳州市",
                "name": "市辖区",
                "id": "350601"
            },
            {
                "city": "漳州市",
                "name": "芗城区",
                "id": "350602"
            },
            {
                "city": "漳州市",
                "name": "龙文区",
                "id": "350603"
            },
            {
                "city": "漳州市",
                "name": "云霄县",
                "id": "350622"
            },
            {
                "city": "漳州市",
                "name": "漳浦县",
                "id": "350623"
            },
            {
                "city": "漳州市",
                "name": "诏安县",
                "id": "350624"
            },
            {
                "city": "漳州市",
                "name": "长泰县",
                "id": "350625"
            },
            {
                "city": "漳州市",
                "name": "东山县",
                "id": "350626"
            },
            {
                "city": "漳州市",
                "name": "南靖县",
                "id": "350627"
            },
            {
                "city": "漳州市",
                "name": "平和县",
                "id": "350628"
            },
            {
                "city": "漳州市",
                "name": "华安县",
                "id": "350629"
            },
            {
                "city": "漳州市",
                "name": "龙海市",
                "id": "350681"
            }
        ],
        "350700": [
            {
                "city": "南平市",
                "name": "市辖区",
                "id": "350701"
            },
            {
                "city": "南平市",
                "name": "延平区",
                "id": "350702"
            },
            {
                "city": "南平市",
                "name": "建阳区",
                "id": "350703"
            },
            {
                "city": "南平市",
                "name": "顺昌县",
                "id": "350721"
            },
            {
                "city": "南平市",
                "name": "浦城县",
                "id": "350722"
            },
            {
                "city": "南平市",
                "name": "光泽县",
                "id": "350723"
            },
            {
                "city": "南平市",
                "name": "松溪县",
                "id": "350724"
            },
            {
                "city": "南平市",
                "name": "政和县",
                "id": "350725"
            },
            {
                "city": "南平市",
                "name": "邵武市",
                "id": "350781"
            },
            {
                "city": "南平市",
                "name": "武夷山市",
                "id": "350782"
            },
            {
                "city": "南平市",
                "name": "建瓯市",
                "id": "350783"
            }
        ],
        "350800": [
            {
                "city": "龙岩市",
                "name": "市辖区",
                "id": "350801"
            },
            {
                "city": "龙岩市",
                "name": "新罗区",
                "id": "350802"
            },
            {
                "city": "龙岩市",
                "name": "永定区",
                "id": "350803"
            },
            {
                "city": "龙岩市",
                "name": "长汀县",
                "id": "350821"
            },
            {
                "city": "龙岩市",
                "name": "上杭县",
                "id": "350823"
            },
            {
                "city": "龙岩市",
                "name": "武平县",
                "id": "350824"
            },
            {
                "city": "龙岩市",
                "name": "连城县",
                "id": "350825"
            },
            {
                "city": "龙岩市",
                "name": "漳平市",
                "id": "350881"
            }
        ],
        "350900": [
            {
                "city": "宁德市",
                "name": "市辖区",
                "id": "350901"
            },
            {
                "city": "宁德市",
                "name": "蕉城区",
                "id": "350902"
            },
            {
                "city": "宁德市",
                "name": "霞浦县",
                "id": "350921"
            },
            {
                "city": "宁德市",
                "name": "古田县",
                "id": "350922"
            },
            {
                "city": "宁德市",
                "name": "屏南县",
                "id": "350923"
            },
            {
                "city": "宁德市",
                "name": "寿宁县",
                "id": "350924"
            },
            {
                "city": "宁德市",
                "name": "周宁县",
                "id": "350925"
            },
            {
                "city": "宁德市",
                "name": "柘荣县",
                "id": "350926"
            },
            {
                "city": "宁德市",
                "name": "福安市",
                "id": "350981"
            },
            {
                "city": "宁德市",
                "name": "福鼎市",
                "id": "350982"
            }
        ],
        "360100": [
            {
                "city": "南昌市",
                "name": "市辖区",
                "id": "360101"
            },
            {
                "city": "南昌市",
                "name": "东湖区",
                "id": "360102"
            },
            {
                "city": "南昌市",
                "name": "西湖区",
                "id": "360103"
            },
            {
                "city": "南昌市",
                "name": "青云谱区",
                "id": "360104"
            },
            {
                "city": "南昌市",
                "name": "湾里区",
                "id": "360105"
            },
            {
                "city": "南昌市",
                "name": "青山湖区",
                "id": "360111"
            },
            {
                "city": "南昌市",
                "name": "新建区",
                "id": "360112"
            },
            {
                "city": "南昌市",
                "name": "南昌县",
                "id": "360121"
            },
            {
                "city": "南昌市",
                "name": "安义县",
                "id": "360123"
            },
            {
                "city": "南昌市",
                "name": "进贤县",
                "id": "360124"
            }
        ],
        "360200": [
            {
                "city": "景德镇市",
                "name": "市辖区",
                "id": "360201"
            },
            {
                "city": "景德镇市",
                "name": "昌江区",
                "id": "360202"
            },
            {
                "city": "景德镇市",
                "name": "珠山区",
                "id": "360203"
            },
            {
                "city": "景德镇市",
                "name": "浮梁县",
                "id": "360222"
            },
            {
                "city": "景德镇市",
                "name": "乐平市",
                "id": "360281"
            }
        ],
        "360300": [
            {
                "city": "萍乡市",
                "name": "市辖区",
                "id": "360301"
            },
            {
                "city": "萍乡市",
                "name": "安源区",
                "id": "360302"
            },
            {
                "city": "萍乡市",
                "name": "湘东区",
                "id": "360313"
            },
            {
                "city": "萍乡市",
                "name": "莲花县",
                "id": "360321"
            },
            {
                "city": "萍乡市",
                "name": "上栗县",
                "id": "360322"
            },
            {
                "city": "萍乡市",
                "name": "芦溪县",
                "id": "360323"
            }
        ],
        "360400": [
            {
                "city": "九江市",
                "name": "市辖区",
                "id": "360401"
            },
            {
                "city": "九江市",
                "name": "濂溪区",
                "id": "360402"
            },
            {
                "city": "九江市",
                "name": "浔阳区",
                "id": "360403"
            },
            {
                "city": "九江市",
                "name": "九江县",
                "id": "360421"
            },
            {
                "city": "九江市",
                "name": "武宁县",
                "id": "360423"
            },
            {
                "city": "九江市",
                "name": "修水县",
                "id": "360424"
            },
            {
                "city": "九江市",
                "name": "永修县",
                "id": "360425"
            },
            {
                "city": "九江市",
                "name": "德安县",
                "id": "360426"
            },
            {
                "city": "九江市",
                "name": "都昌县",
                "id": "360428"
            },
            {
                "city": "九江市",
                "name": "湖口县",
                "id": "360429"
            },
            {
                "city": "九江市",
                "name": "彭泽县",
                "id": "360430"
            },
            {
                "city": "九江市",
                "name": "瑞昌市",
                "id": "360481"
            },
            {
                "city": "九江市",
                "name": "共青城市",
                "id": "360482"
            },
            {
                "city": "九江市",
                "name": "庐山市",
                "id": "360483"
            }
        ],
        "360500": [
            {
                "city": "新余市",
                "name": "市辖区",
                "id": "360501"
            },
            {
                "city": "新余市",
                "name": "渝水区",
                "id": "360502"
            },
            {
                "city": "新余市",
                "name": "分宜县",
                "id": "360521"
            }
        ],
        "360600": [
            {
                "city": "鹰潭市",
                "name": "市辖区",
                "id": "360601"
            },
            {
                "city": "鹰潭市",
                "name": "月湖区",
                "id": "360602"
            },
            {
                "city": "鹰潭市",
                "name": "余江县",
                "id": "360622"
            },
            {
                "city": "鹰潭市",
                "name": "贵溪市",
                "id": "360681"
            }
        ],
        "360700": [
            {
                "city": "赣州市",
                "name": "市辖区",
                "id": "360701"
            },
            {
                "city": "赣州市",
                "name": "章贡区",
                "id": "360702"
            },
            {
                "city": "赣州市",
                "name": "南康区",
                "id": "360703"
            },
            {
                "city": "赣州市",
                "name": "赣县",
                "id": "360721"
            },
            {
                "city": "赣州市",
                "name": "信丰县",
                "id": "360722"
            },
            {
                "city": "赣州市",
                "name": "大余县",
                "id": "360723"
            },
            {
                "city": "赣州市",
                "name": "上犹县",
                "id": "360724"
            },
            {
                "city": "赣州市",
                "name": "崇义县",
                "id": "360725"
            },
            {
                "city": "赣州市",
                "name": "安远县",
                "id": "360726"
            },
            {
                "city": "赣州市",
                "name": "龙南县",
                "id": "360727"
            },
            {
                "city": "赣州市",
                "name": "定南县",
                "id": "360728"
            },
            {
                "city": "赣州市",
                "name": "全南县",
                "id": "360729"
            },
            {
                "city": "赣州市",
                "name": "宁都县",
                "id": "360730"
            },
            {
                "city": "赣州市",
                "name": "于都县",
                "id": "360731"
            },
            {
                "city": "赣州市",
                "name": "兴国县",
                "id": "360732"
            },
            {
                "city": "赣州市",
                "name": "会昌县",
                "id": "360733"
            },
            {
                "city": "赣州市",
                "name": "寻乌县",
                "id": "360734"
            },
            {
                "city": "赣州市",
                "name": "石城县",
                "id": "360735"
            },
            {
                "city": "赣州市",
                "name": "瑞金市",
                "id": "360781"
            }
        ],
        "360800": [
            {
                "city": "吉安市",
                "name": "市辖区",
                "id": "360801"
            },
            {
                "city": "吉安市",
                "name": "吉州区",
                "id": "360802"
            },
            {
                "city": "吉安市",
                "name": "青原区",
                "id": "360803"
            },
            {
                "city": "吉安市",
                "name": "吉安县",
                "id": "360821"
            },
            {
                "city": "吉安市",
                "name": "吉水县",
                "id": "360822"
            },
            {
                "city": "吉安市",
                "name": "峡江县",
                "id": "360823"
            },
            {
                "city": "吉安市",
                "name": "新干县",
                "id": "360824"
            },
            {
                "city": "吉安市",
                "name": "永丰县",
                "id": "360825"
            },
            {
                "city": "吉安市",
                "name": "泰和县",
                "id": "360826"
            },
            {
                "city": "吉安市",
                "name": "遂川县",
                "id": "360827"
            },
            {
                "city": "吉安市",
                "name": "万安县",
                "id": "360828"
            },
            {
                "city": "吉安市",
                "name": "安福县",
                "id": "360829"
            },
            {
                "city": "吉安市",
                "name": "永新县",
                "id": "360830"
            },
            {
                "city": "吉安市",
                "name": "井冈山市",
                "id": "360881"
            }
        ],
        "360900": [
            {
                "city": "宜春市",
                "name": "市辖区",
                "id": "360901"
            },
            {
                "city": "宜春市",
                "name": "袁州区",
                "id": "360902"
            },
            {
                "city": "宜春市",
                "name": "奉新县",
                "id": "360921"
            },
            {
                "city": "宜春市",
                "name": "万载县",
                "id": "360922"
            },
            {
                "city": "宜春市",
                "name": "上高县",
                "id": "360923"
            },
            {
                "city": "宜春市",
                "name": "宜丰县",
                "id": "360924"
            },
            {
                "city": "宜春市",
                "name": "靖安县",
                "id": "360925"
            },
            {
                "city": "宜春市",
                "name": "铜鼓县",
                "id": "360926"
            },
            {
                "city": "宜春市",
                "name": "丰城市",
                "id": "360981"
            },
            {
                "city": "宜春市",
                "name": "樟树市",
                "id": "360982"
            },
            {
                "city": "宜春市",
                "name": "高安市",
                "id": "360983"
            }
        ],
        "361000": [
            {
                "city": "抚州市",
                "name": "市辖区",
                "id": "361001"
            },
            {
                "city": "抚州市",
                "name": "临川区",
                "id": "361002"
            },
            {
                "city": "抚州市",
                "name": "南城县",
                "id": "361021"
            },
            {
                "city": "抚州市",
                "name": "黎川县",
                "id": "361022"
            },
            {
                "city": "抚州市",
                "name": "南丰县",
                "id": "361023"
            },
            {
                "city": "抚州市",
                "name": "崇仁县",
                "id": "361024"
            },
            {
                "city": "抚州市",
                "name": "乐安县",
                "id": "361025"
            },
            {
                "city": "抚州市",
                "name": "宜黄县",
                "id": "361026"
            },
            {
                "city": "抚州市",
                "name": "金溪县",
                "id": "361027"
            },
            {
                "city": "抚州市",
                "name": "资溪县",
                "id": "361028"
            },
            {
                "city": "抚州市",
                "name": "东乡县",
                "id": "361029"
            },
            {
                "city": "抚州市",
                "name": "广昌县",
                "id": "361030"
            }
        ],
        "361100": [
            {
                "city": "上饶市",
                "name": "市辖区",
                "id": "361101"
            },
            {
                "city": "上饶市",
                "name": "信州区",
                "id": "361102"
            },
            {
                "city": "上饶市",
                "name": "广丰区",
                "id": "361103"
            },
            {
                "city": "上饶市",
                "name": "上饶县",
                "id": "361121"
            },
            {
                "city": "上饶市",
                "name": "玉山县",
                "id": "361123"
            },
            {
                "city": "上饶市",
                "name": "铅山县",
                "id": "361124"
            },
            {
                "city": "上饶市",
                "name": "横峰县",
                "id": "361125"
            },
            {
                "city": "上饶市",
                "name": "弋阳县",
                "id": "361126"
            },
            {
                "city": "上饶市",
                "name": "余干县",
                "id": "361127"
            },
            {
                "city": "上饶市",
                "name": "鄱阳县",
                "id": "361128"
            },
            {
                "city": "上饶市",
                "name": "万年县",
                "id": "361129"
            },
            {
                "city": "上饶市",
                "name": "婺源县",
                "id": "361130"
            },
            {
                "city": "上饶市",
                "name": "德兴市",
                "id": "361181"
            }
        ],
        "370100": [
            {
                "city": "济南市",
                "name": "市辖区",
                "id": "370101"
            },
            {
                "city": "济南市",
                "name": "历下区",
                "id": "370102"
            },
            {
                "city": "济南市",
                "name": "市中区",
                "id": "370103"
            },
            {
                "city": "济南市",
                "name": "槐荫区",
                "id": "370104"
            },
            {
                "city": "济南市",
                "name": "天桥区",
                "id": "370105"
            },
            {
                "city": "济南市",
                "name": "历城区",
                "id": "370112"
            },
            {
                "city": "济南市",
                "name": "长清区",
                "id": "370113"
            },
            {
                "city": "济南市",
                "name": "平阴县",
                "id": "370124"
            },
            {
                "city": "济南市",
                "name": "济阳县",
                "id": "370125"
            },
            {
                "city": "济南市",
                "name": "商河县",
                "id": "370126"
            },
            {
                "city": "济南市",
                "name": "章丘市",
                "id": "370181"
            }
        ],
        "370200": [
            {
                "city": "青岛市",
                "name": "市辖区",
                "id": "370201"
            },
            {
                "city": "青岛市",
                "name": "市南区",
                "id": "370202"
            },
            {
                "city": "青岛市",
                "name": "市北区",
                "id": "370203"
            },
            {
                "city": "青岛市",
                "name": "黄岛区",
                "id": "370211"
            },
            {
                "city": "青岛市",
                "name": "崂山区",
                "id": "370212"
            },
            {
                "city": "青岛市",
                "name": "李沧区",
                "id": "370213"
            },
            {
                "city": "青岛市",
                "name": "城阳区",
                "id": "370214"
            },
            {
                "city": "青岛市",
                "name": "胶州市",
                "id": "370281"
            },
            {
                "city": "青岛市",
                "name": "即墨市",
                "id": "370282"
            },
            {
                "city": "青岛市",
                "name": "平度市",
                "id": "370283"
            },
            {
                "city": "青岛市",
                "name": "莱西市",
                "id": "370285"
            }
        ],
        "370300": [
            {
                "city": "淄博市",
                "name": "市辖区",
                "id": "370301"
            },
            {
                "city": "淄博市",
                "name": "淄川区",
                "id": "370302"
            },
            {
                "city": "淄博市",
                "name": "张店区",
                "id": "370303"
            },
            {
                "city": "淄博市",
                "name": "博山区",
                "id": "370304"
            },
            {
                "city": "淄博市",
                "name": "临淄区",
                "id": "370305"
            },
            {
                "city": "淄博市",
                "name": "周村区",
                "id": "370306"
            },
            {
                "city": "淄博市",
                "name": "桓台县",
                "id": "370321"
            },
            {
                "city": "淄博市",
                "name": "高青县",
                "id": "370322"
            },
            {
                "city": "淄博市",
                "name": "沂源县",
                "id": "370323"
            }
        ],
        "370400": [
            {
                "city": "枣庄市",
                "name": "市辖区",
                "id": "370401"
            },
            {
                "city": "枣庄市",
                "name": "市中区",
                "id": "370402"
            },
            {
                "city": "枣庄市",
                "name": "薛城区",
                "id": "370403"
            },
            {
                "city": "枣庄市",
                "name": "峄城区",
                "id": "370404"
            },
            {
                "city": "枣庄市",
                "name": "台儿庄区",
                "id": "370405"
            },
            {
                "city": "枣庄市",
                "name": "山亭区",
                "id": "370406"
            },
            {
                "city": "枣庄市",
                "name": "滕州市",
                "id": "370481"
            }
        ],
        "370500": [
            {
                "city": "东营市",
                "name": "市辖区",
                "id": "370501"
            },
            {
                "city": "东营市",
                "name": "东营区",
                "id": "370502"
            },
            {
                "city": "东营市",
                "name": "河口区",
                "id": "370503"
            },
            {
                "city": "东营市",
                "name": "垦利区",
                "id": "370505"
            },
            {
                "city": "东营市",
                "name": "利津县",
                "id": "370522"
            },
            {
                "city": "东营市",
                "name": "广饶县",
                "id": "370523"
            }
        ],
        "370600": [
            {
                "city": "烟台市",
                "name": "市辖区",
                "id": "370601"
            },
            {
                "city": "烟台市",
                "name": "芝罘区",
                "id": "370602"
            },
            {
                "city": "烟台市",
                "name": "福山区",
                "id": "370611"
            },
            {
                "city": "烟台市",
                "name": "牟平区",
                "id": "370612"
            },
            {
                "city": "烟台市",
                "name": "莱山区",
                "id": "370613"
            },
            {
                "city": "烟台市",
                "name": "长岛县",
                "id": "370634"
            },
            {
                "city": "烟台市",
                "name": "龙口市",
                "id": "370681"
            },
            {
                "city": "烟台市",
                "name": "莱阳市",
                "id": "370682"
            },
            {
                "city": "烟台市",
                "name": "莱州市",
                "id": "370683"
            },
            {
                "city": "烟台市",
                "name": "蓬莱市",
                "id": "370684"
            },
            {
                "city": "烟台市",
                "name": "招远市",
                "id": "370685"
            },
            {
                "city": "烟台市",
                "name": "栖霞市",
                "id": "370686"
            },
            {
                "city": "烟台市",
                "name": "海阳市",
                "id": "370687"
            }
        ],
        "370700": [
            {
                "city": "潍坊市",
                "name": "市辖区",
                "id": "370701"
            },
            {
                "city": "潍坊市",
                "name": "潍城区",
                "id": "370702"
            },
            {
                "city": "潍坊市",
                "name": "寒亭区",
                "id": "370703"
            },
            {
                "city": "潍坊市",
                "name": "坊子区",
                "id": "370704"
            },
            {
                "city": "潍坊市",
                "name": "奎文区",
                "id": "370705"
            },
            {
                "city": "潍坊市",
                "name": "临朐县",
                "id": "370724"
            },
            {
                "city": "潍坊市",
                "name": "昌乐县",
                "id": "370725"
            },
            {
                "city": "潍坊市",
                "name": "青州市",
                "id": "370781"
            },
            {
                "city": "潍坊市",
                "name": "诸城市",
                "id": "370782"
            },
            {
                "city": "潍坊市",
                "name": "寿光市",
                "id": "370783"
            },
            {
                "city": "潍坊市",
                "name": "安丘市",
                "id": "370784"
            },
            {
                "city": "潍坊市",
                "name": "高密市",
                "id": "370785"
            },
            {
                "city": "潍坊市",
                "name": "昌邑市",
                "id": "370786"
            }
        ],
        "370800": [
            {
                "city": "济宁市",
                "name": "市辖区",
                "id": "370801"
            },
            {
                "city": "济宁市",
                "name": "任城区",
                "id": "370811"
            },
            {
                "city": "济宁市",
                "name": "兖州区",
                "id": "370812"
            },
            {
                "city": "济宁市",
                "name": "微山县",
                "id": "370826"
            },
            {
                "city": "济宁市",
                "name": "鱼台县",
                "id": "370827"
            },
            {
                "city": "济宁市",
                "name": "金乡县",
                "id": "370828"
            },
            {
                "city": "济宁市",
                "name": "嘉祥县",
                "id": "370829"
            },
            {
                "city": "济宁市",
                "name": "汶上县",
                "id": "370830"
            },
            {
                "city": "济宁市",
                "name": "泗水县",
                "id": "370831"
            },
            {
                "city": "济宁市",
                "name": "梁山县",
                "id": "370832"
            },
            {
                "city": "济宁市",
                "name": "曲阜市",
                "id": "370881"
            },
            {
                "city": "济宁市",
                "name": "邹城市",
                "id": "370883"
            }
        ],
        "370900": [
            {
                "city": "泰安市",
                "name": "市辖区",
                "id": "370901"
            },
            {
                "city": "泰安市",
                "name": "泰山区",
                "id": "370902"
            },
            {
                "city": "泰安市",
                "name": "岱岳区",
                "id": "370911"
            },
            {
                "city": "泰安市",
                "name": "宁阳县",
                "id": "370921"
            },
            {
                "city": "泰安市",
                "name": "东平县",
                "id": "370923"
            },
            {
                "city": "泰安市",
                "name": "新泰市",
                "id": "370982"
            },
            {
                "city": "泰安市",
                "name": "肥城市",
                "id": "370983"
            }
        ],
        "371000": [
            {
                "city": "威海市",
                "name": "市辖区",
                "id": "371001"
            },
            {
                "city": "威海市",
                "name": "环翠区",
                "id": "371002"
            },
            {
                "city": "威海市",
                "name": "文登区",
                "id": "371003"
            },
            {
                "city": "威海市",
                "name": "荣成市",
                "id": "371082"
            },
            {
                "city": "威海市",
                "name": "乳山市",
                "id": "371083"
            }
        ],
        "371100": [
            {
                "city": "日照市",
                "name": "市辖区",
                "id": "371101"
            },
            {
                "city": "日照市",
                "name": "东港区",
                "id": "371102"
            },
            {
                "city": "日照市",
                "name": "岚山区",
                "id": "371103"
            },
            {
                "city": "日照市",
                "name": "五莲县",
                "id": "371121"
            },
            {
                "city": "日照市",
                "name": "莒县",
                "id": "371122"
            }
        ],
        "371200": [
            {
                "city": "莱芜市",
                "name": "市辖区",
                "id": "371201"
            },
            {
                "city": "莱芜市",
                "name": "莱城区",
                "id": "371202"
            },
            {
                "city": "莱芜市",
                "name": "钢城区",
                "id": "371203"
            }
        ],
        "371300": [
            {
                "city": "临沂市",
                "name": "市辖区",
                "id": "371301"
            },
            {
                "city": "临沂市",
                "name": "兰山区",
                "id": "371302"
            },
            {
                "city": "临沂市",
                "name": "罗庄区",
                "id": "371311"
            },
            {
                "city": "临沂市",
                "name": "河东区",
                "id": "371312"
            },
            {
                "city": "临沂市",
                "name": "沂南县",
                "id": "371321"
            },
            {
                "city": "临沂市",
                "name": "郯城县",
                "id": "371322"
            },
            {
                "city": "临沂市",
                "name": "沂水县",
                "id": "371323"
            },
            {
                "city": "临沂市",
                "name": "兰陵县",
                "id": "371324"
            },
            {
                "city": "临沂市",
                "name": "费县",
                "id": "371325"
            },
            {
                "city": "临沂市",
                "name": "平邑县",
                "id": "371326"
            },
            {
                "city": "临沂市",
                "name": "莒南县",
                "id": "371327"
            },
            {
                "city": "临沂市",
                "name": "蒙阴县",
                "id": "371328"
            },
            {
                "city": "临沂市",
                "name": "临沭县",
                "id": "371329"
            }
        ],
        "371400": [
            {
                "city": "德州市",
                "name": "市辖区",
                "id": "371401"
            },
            {
                "city": "德州市",
                "name": "德城区",
                "id": "371402"
            },
            {
                "city": "德州市",
                "name": "陵城区",
                "id": "371403"
            },
            {
                "city": "德州市",
                "name": "宁津县",
                "id": "371422"
            },
            {
                "city": "德州市",
                "name": "庆云县",
                "id": "371423"
            },
            {
                "city": "德州市",
                "name": "临邑县",
                "id": "371424"
            },
            {
                "city": "德州市",
                "name": "齐河县",
                "id": "371425"
            },
            {
                "city": "德州市",
                "name": "平原县",
                "id": "371426"
            },
            {
                "city": "德州市",
                "name": "夏津县",
                "id": "371427"
            },
            {
                "city": "德州市",
                "name": "武城县",
                "id": "371428"
            },
            {
                "city": "德州市",
                "name": "乐陵市",
                "id": "371481"
            },
            {
                "city": "德州市",
                "name": "禹城市",
                "id": "371482"
            }
        ],
        "371500": [
            {
                "city": "聊城市",
                "name": "市辖区",
                "id": "371501"
            },
            {
                "city": "聊城市",
                "name": "东昌府区",
                "id": "371502"
            },
            {
                "city": "聊城市",
                "name": "阳谷县",
                "id": "371521"
            },
            {
                "city": "聊城市",
                "name": "莘县",
                "id": "371522"
            },
            {
                "city": "聊城市",
                "name": "茌平县",
                "id": "371523"
            },
            {
                "city": "聊城市",
                "name": "东阿县",
                "id": "371524"
            },
            {
                "city": "聊城市",
                "name": "冠县",
                "id": "371525"
            },
            {
                "city": "聊城市",
                "name": "高唐县",
                "id": "371526"
            },
            {
                "city": "聊城市",
                "name": "临清市",
                "id": "371581"
            }
        ],
        "371600": [
            {
                "city": "滨州市",
                "name": "市辖区",
                "id": "371601"
            },
            {
                "city": "滨州市",
                "name": "滨城区",
                "id": "371602"
            },
            {
                "city": "滨州市",
                "name": "沾化区",
                "id": "371603"
            },
            {
                "city": "滨州市",
                "name": "惠民县",
                "id": "371621"
            },
            {
                "city": "滨州市",
                "name": "阳信县",
                "id": "371622"
            },
            {
                "city": "滨州市",
                "name": "无棣县",
                "id": "371623"
            },
            {
                "city": "滨州市",
                "name": "博兴县",
                "id": "371625"
            },
            {
                "city": "滨州市",
                "name": "邹平县",
                "id": "371626"
            }
        ],
        "371700": [
            {
                "city": "菏泽市",
                "name": "市辖区",
                "id": "371701"
            },
            {
                "city": "菏泽市",
                "name": "牡丹区",
                "id": "371702"
            },
            {
                "city": "菏泽市",
                "name": "定陶区",
                "id": "371703"
            },
            {
                "city": "菏泽市",
                "name": "曹县",
                "id": "371721"
            },
            {
                "city": "菏泽市",
                "name": "单县",
                "id": "371722"
            },
            {
                "city": "菏泽市",
                "name": "成武县",
                "id": "371723"
            },
            {
                "city": "菏泽市",
                "name": "巨野县",
                "id": "371724"
            },
            {
                "city": "菏泽市",
                "name": "郓城县",
                "id": "371725"
            },
            {
                "city": "菏泽市",
                "name": "鄄城县",
                "id": "371726"
            },
            {
                "city": "菏泽市",
                "name": "东明县",
                "id": "371728"
            }
        ],
        "410100": [
            {
                "city": "郑州市",
                "name": "市辖区",
                "id": "410101"
            },
            {
                "city": "郑州市",
                "name": "中原区",
                "id": "410102"
            },
            {
                "city": "郑州市",
                "name": "二七区",
                "id": "410103"
            },
            {
                "city": "郑州市",
                "name": "管城回族区",
                "id": "410104"
            },
            {
                "city": "郑州市",
                "name": "金水区",
                "id": "410105"
            },
            {
                "city": "郑州市",
                "name": "上街区",
                "id": "410106"
            },
            {
                "city": "郑州市",
                "name": "惠济区",
                "id": "410108"
            },
            {
                "city": "郑州市",
                "name": "中牟县",
                "id": "410122"
            },
            {
                "city": "郑州市",
                "name": "巩义市",
                "id": "410181"
            },
            {
                "city": "郑州市",
                "name": "荥阳市",
                "id": "410182"
            },
            {
                "city": "郑州市",
                "name": "新密市",
                "id": "410183"
            },
            {
                "city": "郑州市",
                "name": "新郑市",
                "id": "410184"
            },
            {
                "city": "郑州市",
                "name": "登封市",
                "id": "410185"
            }
        ],
        "410200": [
            {
                "city": "开封市",
                "name": "市辖区",
                "id": "410201"
            },
            {
                "city": "开封市",
                "name": "龙亭区",
                "id": "410202"
            },
            {
                "city": "开封市",
                "name": "顺河回族区",
                "id": "410203"
            },
            {
                "city": "开封市",
                "name": "鼓楼区",
                "id": "410204"
            },
            {
                "city": "开封市",
                "name": "禹王台区",
                "id": "410205"
            },
            {
                "city": "开封市",
                "name": "金明区",
                "id": "410211"
            },
            {
                "city": "开封市",
                "name": "祥符区",
                "id": "410212"
            },
            {
                "city": "开封市",
                "name": "杞县",
                "id": "410221"
            },
            {
                "city": "开封市",
                "name": "通许县",
                "id": "410222"
            },
            {
                "city": "开封市",
                "name": "尉氏县",
                "id": "410223"
            },
            {
                "city": "开封市",
                "name": "兰考县",
                "id": "410225"
            }
        ],
        "410300": [
            {
                "city": "洛阳市",
                "name": "市辖区",
                "id": "410301"
            },
            {
                "city": "洛阳市",
                "name": "老城区",
                "id": "410302"
            },
            {
                "city": "洛阳市",
                "name": "西工区",
                "id": "410303"
            },
            {
                "city": "洛阳市",
                "name": "瀍河回族区",
                "id": "410304"
            },
            {
                "city": "洛阳市",
                "name": "涧西区",
                "id": "410305"
            },
            {
                "city": "洛阳市",
                "name": "吉利区",
                "id": "410306"
            },
            {
                "city": "洛阳市",
                "name": "洛龙区",
                "id": "410311"
            },
            {
                "city": "洛阳市",
                "name": "孟津县",
                "id": "410322"
            },
            {
                "city": "洛阳市",
                "name": "新安县",
                "id": "410323"
            },
            {
                "city": "洛阳市",
                "name": "栾川县",
                "id": "410324"
            },
            {
                "city": "洛阳市",
                "name": "嵩县",
                "id": "410325"
            },
            {
                "city": "洛阳市",
                "name": "汝阳县",
                "id": "410326"
            },
            {
                "city": "洛阳市",
                "name": "宜阳县",
                "id": "410327"
            },
            {
                "city": "洛阳市",
                "name": "洛宁县",
                "id": "410328"
            },
            {
                "city": "洛阳市",
                "name": "伊川县",
                "id": "410329"
            },
            {
                "city": "洛阳市",
                "name": "偃师市",
                "id": "410381"
            }
        ],
        "410400": [
            {
                "city": "平顶山市",
                "name": "市辖区",
                "id": "410401"
            },
            {
                "city": "平顶山市",
                "name": "新华区",
                "id": "410402"
            },
            {
                "city": "平顶山市",
                "name": "卫东区",
                "id": "410403"
            },
            {
                "city": "平顶山市",
                "name": "石龙区",
                "id": "410404"
            },
            {
                "city": "平顶山市",
                "name": "湛河区",
                "id": "410411"
            },
            {
                "city": "平顶山市",
                "name": "宝丰县",
                "id": "410421"
            },
            {
                "city": "平顶山市",
                "name": "叶县",
                "id": "410422"
            },
            {
                "city": "平顶山市",
                "name": "鲁山县",
                "id": "410423"
            },
            {
                "city": "平顶山市",
                "name": "郏县",
                "id": "410425"
            },
            {
                "city": "平顶山市",
                "name": "舞钢市",
                "id": "410481"
            },
            {
                "city": "平顶山市",
                "name": "汝州市",
                "id": "410482"
            }
        ],
        "410500": [
            {
                "city": "安阳市",
                "name": "市辖区",
                "id": "410501"
            },
            {
                "city": "安阳市",
                "name": "文峰区",
                "id": "410502"
            },
            {
                "city": "安阳市",
                "name": "北关区",
                "id": "410503"
            },
            {
                "city": "安阳市",
                "name": "殷都区",
                "id": "410505"
            },
            {
                "city": "安阳市",
                "name": "龙安区",
                "id": "410506"
            },
            {
                "city": "安阳市",
                "name": "安阳县",
                "id": "410522"
            },
            {
                "city": "安阳市",
                "name": "汤阴县",
                "id": "410523"
            },
            {
                "city": "安阳市",
                "name": "滑县",
                "id": "410526"
            },
            {
                "city": "安阳市",
                "name": "内黄县",
                "id": "410527"
            },
            {
                "city": "安阳市",
                "name": "林州市",
                "id": "410581"
            }
        ],
        "410600": [
            {
                "city": "鹤壁市",
                "name": "市辖区",
                "id": "410601"
            },
            {
                "city": "鹤壁市",
                "name": "鹤山区",
                "id": "410602"
            },
            {
                "city": "鹤壁市",
                "name": "山城区",
                "id": "410603"
            },
            {
                "city": "鹤壁市",
                "name": "淇滨区",
                "id": "410611"
            },
            {
                "city": "鹤壁市",
                "name": "浚县",
                "id": "410621"
            },
            {
                "city": "鹤壁市",
                "name": "淇县",
                "id": "410622"
            }
        ],
        "410700": [
            {
                "city": "新乡市",
                "name": "市辖区",
                "id": "410701"
            },
            {
                "city": "新乡市",
                "name": "红旗区",
                "id": "410702"
            },
            {
                "city": "新乡市",
                "name": "卫滨区",
                "id": "410703"
            },
            {
                "city": "新乡市",
                "name": "凤泉区",
                "id": "410704"
            },
            {
                "city": "新乡市",
                "name": "牧野区",
                "id": "410711"
            },
            {
                "city": "新乡市",
                "name": "新乡县",
                "id": "410721"
            },
            {
                "city": "新乡市",
                "name": "获嘉县",
                "id": "410724"
            },
            {
                "city": "新乡市",
                "name": "原阳县",
                "id": "410725"
            },
            {
                "city": "新乡市",
                "name": "延津县",
                "id": "410726"
            },
            {
                "city": "新乡市",
                "name": "封丘县",
                "id": "410727"
            },
            {
                "city": "新乡市",
                "name": "长垣县",
                "id": "410728"
            },
            {
                "city": "新乡市",
                "name": "卫辉市",
                "id": "410781"
            },
            {
                "city": "新乡市",
                "name": "辉县市",
                "id": "410782"
            }
        ],
        "410800": [
            {
                "city": "焦作市",
                "name": "市辖区",
                "id": "410801"
            },
            {
                "city": "焦作市",
                "name": "解放区",
                "id": "410802"
            },
            {
                "city": "焦作市",
                "name": "中站区",
                "id": "410803"
            },
            {
                "city": "焦作市",
                "name": "马村区",
                "id": "410804"
            },
            {
                "city": "焦作市",
                "name": "山阳区",
                "id": "410811"
            },
            {
                "city": "焦作市",
                "name": "修武县",
                "id": "410821"
            },
            {
                "city": "焦作市",
                "name": "博爱县",
                "id": "410822"
            },
            {
                "city": "焦作市",
                "name": "武陟县",
                "id": "410823"
            },
            {
                "city": "焦作市",
                "name": "温县",
                "id": "410825"
            },
            {
                "city": "焦作市",
                "name": "沁阳市",
                "id": "410882"
            },
            {
                "city": "焦作市",
                "name": "孟州市",
                "id": "410883"
            }
        ],
        "410900": [
            {
                "city": "濮阳市",
                "name": "市辖区",
                "id": "410901"
            },
            {
                "city": "濮阳市",
                "name": "华龙区",
                "id": "410902"
            },
            {
                "city": "濮阳市",
                "name": "清丰县",
                "id": "410922"
            },
            {
                "city": "濮阳市",
                "name": "南乐县",
                "id": "410923"
            },
            {
                "city": "濮阳市",
                "name": "范县",
                "id": "410926"
            },
            {
                "city": "濮阳市",
                "name": "台前县",
                "id": "410927"
            },
            {
                "city": "濮阳市",
                "name": "濮阳县",
                "id": "410928"
            }
        ],
        "411000": [
            {
                "city": "许昌市",
                "name": "市辖区",
                "id": "411001"
            },
            {
                "city": "许昌市",
                "name": "魏都区",
                "id": "411002"
            },
            {
                "city": "许昌市",
                "name": "许昌县",
                "id": "411023"
            },
            {
                "city": "许昌市",
                "name": "鄢陵县",
                "id": "411024"
            },
            {
                "city": "许昌市",
                "name": "襄城县",
                "id": "411025"
            },
            {
                "city": "许昌市",
                "name": "禹州市",
                "id": "411081"
            },
            {
                "city": "许昌市",
                "name": "长葛市",
                "id": "411082"
            }
        ],
        "411100": [
            {
                "city": "漯河市",
                "name": "市辖区",
                "id": "411101"
            },
            {
                "city": "漯河市",
                "name": "源汇区",
                "id": "411102"
            },
            {
                "city": "漯河市",
                "name": "郾城区",
                "id": "411103"
            },
            {
                "city": "漯河市",
                "name": "召陵区",
                "id": "411104"
            },
            {
                "city": "漯河市",
                "name": "舞阳县",
                "id": "411121"
            },
            {
                "city": "漯河市",
                "name": "临颍县",
                "id": "411122"
            }
        ],
        "411200": [
            {
                "city": "三门峡市",
                "name": "市辖区",
                "id": "411201"
            },
            {
                "city": "三门峡市",
                "name": "湖滨区",
                "id": "411202"
            },
            {
                "city": "三门峡市",
                "name": "陕州区",
                "id": "411203"
            },
            {
                "city": "三门峡市",
                "name": "渑池县",
                "id": "411221"
            },
            {
                "city": "三门峡市",
                "name": "卢氏县",
                "id": "411224"
            },
            {
                "city": "三门峡市",
                "name": "义马市",
                "id": "411281"
            },
            {
                "city": "三门峡市",
                "name": "灵宝市",
                "id": "411282"
            }
        ],
        "411300": [
            {
                "city": "南阳市",
                "name": "市辖区",
                "id": "411301"
            },
            {
                "city": "南阳市",
                "name": "宛城区",
                "id": "411302"
            },
            {
                "city": "南阳市",
                "name": "卧龙区",
                "id": "411303"
            },
            {
                "city": "南阳市",
                "name": "南召县",
                "id": "411321"
            },
            {
                "city": "南阳市",
                "name": "方城县",
                "id": "411322"
            },
            {
                "city": "南阳市",
                "name": "西峡县",
                "id": "411323"
            },
            {
                "city": "南阳市",
                "name": "镇平县",
                "id": "411324"
            },
            {
                "city": "南阳市",
                "name": "内乡县",
                "id": "411325"
            },
            {
                "city": "南阳市",
                "name": "淅川县",
                "id": "411326"
            },
            {
                "city": "南阳市",
                "name": "社旗县",
                "id": "411327"
            },
            {
                "city": "南阳市",
                "name": "唐河县",
                "id": "411328"
            },
            {
                "city": "南阳市",
                "name": "新野县",
                "id": "411329"
            },
            {
                "city": "南阳市",
                "name": "桐柏县",
                "id": "411330"
            },
            {
                "city": "南阳市",
                "name": "邓州市",
                "id": "411381"
            }
        ],
        "411400": [
            {
                "city": "商丘市",
                "name": "市辖区",
                "id": "411401"
            },
            {
                "city": "商丘市",
                "name": "梁园区",
                "id": "411402"
            },
            {
                "city": "商丘市",
                "name": "睢阳区",
                "id": "411403"
            },
            {
                "city": "商丘市",
                "name": "民权县",
                "id": "411421"
            },
            {
                "city": "商丘市",
                "name": "睢县",
                "id": "411422"
            },
            {
                "city": "商丘市",
                "name": "宁陵县",
                "id": "411423"
            },
            {
                "city": "商丘市",
                "name": "柘城县",
                "id": "411424"
            },
            {
                "city": "商丘市",
                "name": "虞城县",
                "id": "411425"
            },
            {
                "city": "商丘市",
                "name": "夏邑县",
                "id": "411426"
            },
            {
                "city": "商丘市",
                "name": "永城市",
                "id": "411481"
            }
        ],
        "411500": [
            {
                "city": "信阳市",
                "name": "市辖区",
                "id": "411501"
            },
            {
                "city": "信阳市",
                "name": "浉河区",
                "id": "411502"
            },
            {
                "city": "信阳市",
                "name": "平桥区",
                "id": "411503"
            },
            {
                "city": "信阳市",
                "name": "罗山县",
                "id": "411521"
            },
            {
                "city": "信阳市",
                "name": "光山县",
                "id": "411522"
            },
            {
                "city": "信阳市",
                "name": "新县",
                "id": "411523"
            },
            {
                "city": "信阳市",
                "name": "商城县",
                "id": "411524"
            },
            {
                "city": "信阳市",
                "name": "固始县",
                "id": "411525"
            },
            {
                "city": "信阳市",
                "name": "潢川县",
                "id": "411526"
            },
            {
                "city": "信阳市",
                "name": "淮滨县",
                "id": "411527"
            },
            {
                "city": "信阳市",
                "name": "息县",
                "id": "411528"
            }
        ],
        "411600": [
            {
                "city": "周口市",
                "name": "市辖区",
                "id": "411601"
            },
            {
                "city": "周口市",
                "name": "川汇区",
                "id": "411602"
            },
            {
                "city": "周口市",
                "name": "扶沟县",
                "id": "411621"
            },
            {
                "city": "周口市",
                "name": "西华县",
                "id": "411622"
            },
            {
                "city": "周口市",
                "name": "商水县",
                "id": "411623"
            },
            {
                "city": "周口市",
                "name": "沈丘县",
                "id": "411624"
            },
            {
                "city": "周口市",
                "name": "郸城县",
                "id": "411625"
            },
            {
                "city": "周口市",
                "name": "淮阳县",
                "id": "411626"
            },
            {
                "city": "周口市",
                "name": "太康县",
                "id": "411627"
            },
            {
                "city": "周口市",
                "name": "鹿邑县",
                "id": "411628"
            },
            {
                "city": "周口市",
                "name": "项城市",
                "id": "411681"
            }
        ],
        "411700": [
            {
                "city": "驻马店市",
                "name": "市辖区",
                "id": "411701"
            },
            {
                "city": "驻马店市",
                "name": "驿城区",
                "id": "411702"
            },
            {
                "city": "驻马店市",
                "name": "西平县",
                "id": "411721"
            },
            {
                "city": "驻马店市",
                "name": "上蔡县",
                "id": "411722"
            },
            {
                "city": "驻马店市",
                "name": "平舆县",
                "id": "411723"
            },
            {
                "city": "驻马店市",
                "name": "正阳县",
                "id": "411724"
            },
            {
                "city": "驻马店市",
                "name": "确山县",
                "id": "411725"
            },
            {
                "city": "驻马店市",
                "name": "泌阳县",
                "id": "411726"
            },
            {
                "city": "驻马店市",
                "name": "汝南县",
                "id": "411727"
            },
            {
                "city": "驻马店市",
                "name": "遂平县",
                "id": "411728"
            },
            {
                "city": "驻马店市",
                "name": "新蔡县",
                "id": "411729"
            }
        ],
        "419000": [
            {
                "city": "省直辖县级行政区划",
                "name": "济源市",
                "id": "419001"
            }
        ],
        "420100": [
            {
                "city": "武汉市",
                "name": "市辖区",
                "id": "420101"
            },
            {
                "city": "武汉市",
                "name": "江岸区",
                "id": "420102"
            },
            {
                "city": "武汉市",
                "name": "江汉区",
                "id": "420103"
            },
            {
                "city": "武汉市",
                "name": "硚口区",
                "id": "420104"
            },
            {
                "city": "武汉市",
                "name": "汉阳区",
                "id": "420105"
            },
            {
                "city": "武汉市",
                "name": "武昌区",
                "id": "420106"
            },
            {
                "city": "武汉市",
                "name": "青山区",
                "id": "420107"
            },
            {
                "city": "武汉市",
                "name": "洪山区",
                "id": "420111"
            },
            {
                "city": "武汉市",
                "name": "东西湖区",
                "id": "420112"
            },
            {
                "city": "武汉市",
                "name": "汉南区",
                "id": "420113"
            },
            {
                "city": "武汉市",
                "name": "蔡甸区",
                "id": "420114"
            },
            {
                "city": "武汉市",
                "name": "江夏区",
                "id": "420115"
            },
            {
                "city": "武汉市",
                "name": "黄陂区",
                "id": "420116"
            },
            {
                "city": "武汉市",
                "name": "新洲区",
                "id": "420117"
            }
        ],
        "420200": [
            {
                "city": "黄石市",
                "name": "市辖区",
                "id": "420201"
            },
            {
                "city": "黄石市",
                "name": "黄石港区",
                "id": "420202"
            },
            {
                "city": "黄石市",
                "name": "西塞山区",
                "id": "420203"
            },
            {
                "city": "黄石市",
                "name": "下陆区",
                "id": "420204"
            },
            {
                "city": "黄石市",
                "name": "铁山区",
                "id": "420205"
            },
            {
                "city": "黄石市",
                "name": "阳新县",
                "id": "420222"
            },
            {
                "city": "黄石市",
                "name": "大冶市",
                "id": "420281"
            }
        ],
        "420300": [
            {
                "city": "十堰市",
                "name": "市辖区",
                "id": "420301"
            },
            {
                "city": "十堰市",
                "name": "茅箭区",
                "id": "420302"
            },
            {
                "city": "十堰市",
                "name": "张湾区",
                "id": "420303"
            },
            {
                "city": "十堰市",
                "name": "郧阳区",
                "id": "420304"
            },
            {
                "city": "十堰市",
                "name": "郧西县",
                "id": "420322"
            },
            {
                "city": "十堰市",
                "name": "竹山县",
                "id": "420323"
            },
            {
                "city": "十堰市",
                "name": "竹溪县",
                "id": "420324"
            },
            {
                "city": "十堰市",
                "name": "房县",
                "id": "420325"
            },
            {
                "city": "十堰市",
                "name": "丹江口市",
                "id": "420381"
            }
        ],
        "420500": [
            {
                "city": "宜昌市",
                "name": "市辖区",
                "id": "420501"
            },
            {
                "city": "宜昌市",
                "name": "西陵区",
                "id": "420502"
            },
            {
                "city": "宜昌市",
                "name": "伍家岗区",
                "id": "420503"
            },
            {
                "city": "宜昌市",
                "name": "点军区",
                "id": "420504"
            },
            {
                "city": "宜昌市",
                "name": "猇亭区",
                "id": "420505"
            },
            {
                "city": "宜昌市",
                "name": "夷陵区",
                "id": "420506"
            },
            {
                "city": "宜昌市",
                "name": "远安县",
                "id": "420525"
            },
            {
                "city": "宜昌市",
                "name": "兴山县",
                "id": "420526"
            },
            {
                "city": "宜昌市",
                "name": "秭归县",
                "id": "420527"
            },
            {
                "city": "宜昌市",
                "name": "长阳土家族自治县",
                "id": "420528"
            },
            {
                "city": "宜昌市",
                "name": "五峰土家族自治县",
                "id": "420529"
            },
            {
                "city": "宜昌市",
                "name": "宜都市",
                "id": "420581"
            },
            {
                "city": "宜昌市",
                "name": "当阳市",
                "id": "420582"
            },
            {
                "city": "宜昌市",
                "name": "枝江市",
                "id": "420583"
            }
        ],
        "420600": [
            {
                "city": "襄阳市",
                "name": "市辖区",
                "id": "420601"
            },
            {
                "city": "襄阳市",
                "name": "襄城区",
                "id": "420602"
            },
            {
                "city": "襄阳市",
                "name": "樊城区",
                "id": "420606"
            },
            {
                "city": "襄阳市",
                "name": "襄州区",
                "id": "420607"
            },
            {
                "city": "襄阳市",
                "name": "南漳县",
                "id": "420624"
            },
            {
                "city": "襄阳市",
                "name": "谷城县",
                "id": "420625"
            },
            {
                "city": "襄阳市",
                "name": "保康县",
                "id": "420626"
            },
            {
                "city": "襄阳市",
                "name": "老河口市",
                "id": "420682"
            },
            {
                "city": "襄阳市",
                "name": "枣阳市",
                "id": "420683"
            },
            {
                "city": "襄阳市",
                "name": "宜城市",
                "id": "420684"
            }
        ],
        "420700": [
            {
                "city": "鄂州市",
                "name": "市辖区",
                "id": "420701"
            },
            {
                "city": "鄂州市",
                "name": "梁子湖区",
                "id": "420702"
            },
            {
                "city": "鄂州市",
                "name": "华容区",
                "id": "420703"
            },
            {
                "city": "鄂州市",
                "name": "鄂城区",
                "id": "420704"
            }
        ],
        "420800": [
            {
                "city": "荆门市",
                "name": "市辖区",
                "id": "420801"
            },
            {
                "city": "荆门市",
                "name": "东宝区",
                "id": "420802"
            },
            {
                "city": "荆门市",
                "name": "掇刀区",
                "id": "420804"
            },
            {
                "city": "荆门市",
                "name": "京山县",
                "id": "420821"
            },
            {
                "city": "荆门市",
                "name": "沙洋县",
                "id": "420822"
            },
            {
                "city": "荆门市",
                "name": "钟祥市",
                "id": "420881"
            }
        ],
        "420900": [
            {
                "city": "孝感市",
                "name": "市辖区",
                "id": "420901"
            },
            {
                "city": "孝感市",
                "name": "孝南区",
                "id": "420902"
            },
            {
                "city": "孝感市",
                "name": "孝昌县",
                "id": "420921"
            },
            {
                "city": "孝感市",
                "name": "大悟县",
                "id": "420922"
            },
            {
                "city": "孝感市",
                "name": "云梦县",
                "id": "420923"
            },
            {
                "city": "孝感市",
                "name": "应城市",
                "id": "420981"
            },
            {
                "city": "孝感市",
                "name": "安陆市",
                "id": "420982"
            },
            {
                "city": "孝感市",
                "name": "汉川市",
                "id": "420984"
            }
        ],
        "421000": [
            {
                "city": "荆州市",
                "name": "市辖区",
                "id": "421001"
            },
            {
                "city": "荆州市",
                "name": "沙市区",
                "id": "421002"
            },
            {
                "city": "荆州市",
                "name": "荆州区",
                "id": "421003"
            },
            {
                "city": "荆州市",
                "name": "公安县",
                "id": "421022"
            },
            {
                "city": "荆州市",
                "name": "监利县",
                "id": "421023"
            },
            {
                "city": "荆州市",
                "name": "江陵县",
                "id": "421024"
            },
            {
                "city": "荆州市",
                "name": "石首市",
                "id": "421081"
            },
            {
                "city": "荆州市",
                "name": "洪湖市",
                "id": "421083"
            },
            {
                "city": "荆州市",
                "name": "松滋市",
                "id": "421087"
            }
        ],
        "421100": [
            {
                "city": "黄冈市",
                "name": "市辖区",
                "id": "421101"
            },
            {
                "city": "黄冈市",
                "name": "黄州区",
                "id": "421102"
            },
            {
                "city": "黄冈市",
                "name": "团风县",
                "id": "421121"
            },
            {
                "city": "黄冈市",
                "name": "红安县",
                "id": "421122"
            },
            {
                "city": "黄冈市",
                "name": "罗田县",
                "id": "421123"
            },
            {
                "city": "黄冈市",
                "name": "英山县",
                "id": "421124"
            },
            {
                "city": "黄冈市",
                "name": "浠水县",
                "id": "421125"
            },
            {
                "city": "黄冈市",
                "name": "蕲春县",
                "id": "421126"
            },
            {
                "city": "黄冈市",
                "name": "黄梅县",
                "id": "421127"
            },
            {
                "city": "黄冈市",
                "name": "麻城市",
                "id": "421181"
            },
            {
                "city": "黄冈市",
                "name": "武穴市",
                "id": "421182"
            }
        ],
        "421200": [
            {
                "city": "咸宁市",
                "name": "市辖区",
                "id": "421201"
            },
            {
                "city": "咸宁市",
                "name": "咸安区",
                "id": "421202"
            },
            {
                "city": "咸宁市",
                "name": "嘉鱼县",
                "id": "421221"
            },
            {
                "city": "咸宁市",
                "name": "通城县",
                "id": "421222"
            },
            {
                "city": "咸宁市",
                "name": "崇阳县",
                "id": "421223"
            },
            {
                "city": "咸宁市",
                "name": "通山县",
                "id": "421224"
            },
            {
                "city": "咸宁市",
                "name": "赤壁市",
                "id": "421281"
            }
        ],
        "421300": [
            {
                "city": "随州市",
                "name": "市辖区",
                "id": "421301"
            },
            {
                "city": "随州市",
                "name": "曾都区",
                "id": "421303"
            },
            {
                "city": "随州市",
                "name": "随县",
                "id": "421321"
            },
            {
                "city": "随州市",
                "name": "广水市",
                "id": "421381"
            }
        ],
        "422800": [
            {
                "city": "恩施土家族苗族自治州",
                "name": "恩施市",
                "id": "422801"
            },
            {
                "city": "恩施土家族苗族自治州",
                "name": "利川市",
                "id": "422802"
            },
            {
                "city": "恩施土家族苗族自治州",
                "name": "建始县",
                "id": "422822"
            },
            {
                "city": "恩施土家族苗族自治州",
                "name": "巴东县",
                "id": "422823"
            },
            {
                "city": "恩施土家族苗族自治州",
                "name": "宣恩县",
                "id": "422825"
            },
            {
                "city": "恩施土家族苗族自治州",
                "name": "咸丰县",
                "id": "422826"
            },
            {
                "city": "恩施土家族苗族自治州",
                "name": "来凤县",
                "id": "422827"
            },
            {
                "city": "恩施土家族苗族自治州",
                "name": "鹤峰县",
                "id": "422828"
            }
        ],
        "429000": [
            {
                "city": "省直辖县级行政区划",
                "name": "仙桃市",
                "id": "429004"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "潜江市",
                "id": "429005"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "天门市",
                "id": "429006"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "神农架林区",
                "id": "429021"
            }
        ],
        "430100": [
            {
                "city": "长沙市",
                "name": "市辖区",
                "id": "430101"
            },
            {
                "city": "长沙市",
                "name": "芙蓉区",
                "id": "430102"
            },
            {
                "city": "长沙市",
                "name": "天心区",
                "id": "430103"
            },
            {
                "city": "长沙市",
                "name": "岳麓区",
                "id": "430104"
            },
            {
                "city": "长沙市",
                "name": "开福区",
                "id": "430105"
            },
            {
                "city": "长沙市",
                "name": "雨花区",
                "id": "430111"
            },
            {
                "city": "长沙市",
                "name": "望城区",
                "id": "430112"
            },
            {
                "city": "长沙市",
                "name": "长沙县",
                "id": "430121"
            },
            {
                "city": "长沙市",
                "name": "宁乡县",
                "id": "430124"
            },
            {
                "city": "长沙市",
                "name": "浏阳市",
                "id": "430181"
            }
        ],
        "430200": [
            {
                "city": "株洲市",
                "name": "市辖区",
                "id": "430201"
            },
            {
                "city": "株洲市",
                "name": "荷塘区",
                "id": "430202"
            },
            {
                "city": "株洲市",
                "name": "芦淞区",
                "id": "430203"
            },
            {
                "city": "株洲市",
                "name": "石峰区",
                "id": "430204"
            },
            {
                "city": "株洲市",
                "name": "天元区",
                "id": "430211"
            },
            {
                "city": "株洲市",
                "name": "株洲县",
                "id": "430221"
            },
            {
                "city": "株洲市",
                "name": "攸县",
                "id": "430223"
            },
            {
                "city": "株洲市",
                "name": "茶陵县",
                "id": "430224"
            },
            {
                "city": "株洲市",
                "name": "炎陵县",
                "id": "430225"
            },
            {
                "city": "株洲市",
                "name": "醴陵市",
                "id": "430281"
            }
        ],
        "430300": [
            {
                "city": "湘潭市",
                "name": "市辖区",
                "id": "430301"
            },
            {
                "city": "湘潭市",
                "name": "雨湖区",
                "id": "430302"
            },
            {
                "city": "湘潭市",
                "name": "岳塘区",
                "id": "430304"
            },
            {
                "city": "湘潭市",
                "name": "湘潭县",
                "id": "430321"
            },
            {
                "city": "湘潭市",
                "name": "湘乡市",
                "id": "430381"
            },
            {
                "city": "湘潭市",
                "name": "韶山市",
                "id": "430382"
            }
        ],
        "430400": [
            {
                "city": "衡阳市",
                "name": "市辖区",
                "id": "430401"
            },
            {
                "city": "衡阳市",
                "name": "珠晖区",
                "id": "430405"
            },
            {
                "city": "衡阳市",
                "name": "雁峰区",
                "id": "430406"
            },
            {
                "city": "衡阳市",
                "name": "石鼓区",
                "id": "430407"
            },
            {
                "city": "衡阳市",
                "name": "蒸湘区",
                "id": "430408"
            },
            {
                "city": "衡阳市",
                "name": "南岳区",
                "id": "430412"
            },
            {
                "city": "衡阳市",
                "name": "衡阳县",
                "id": "430421"
            },
            {
                "city": "衡阳市",
                "name": "衡南县",
                "id": "430422"
            },
            {
                "city": "衡阳市",
                "name": "衡山县",
                "id": "430423"
            },
            {
                "city": "衡阳市",
                "name": "衡东县",
                "id": "430424"
            },
            {
                "city": "衡阳市",
                "name": "祁东县",
                "id": "430426"
            },
            {
                "city": "衡阳市",
                "name": "耒阳市",
                "id": "430481"
            },
            {
                "city": "衡阳市",
                "name": "常宁市",
                "id": "430482"
            }
        ],
        "430500": [
            {
                "city": "邵阳市",
                "name": "市辖区",
                "id": "430501"
            },
            {
                "city": "邵阳市",
                "name": "双清区",
                "id": "430502"
            },
            {
                "city": "邵阳市",
                "name": "大祥区",
                "id": "430503"
            },
            {
                "city": "邵阳市",
                "name": "北塔区",
                "id": "430511"
            },
            {
                "city": "邵阳市",
                "name": "邵东县",
                "id": "430521"
            },
            {
                "city": "邵阳市",
                "name": "新邵县",
                "id": "430522"
            },
            {
                "city": "邵阳市",
                "name": "邵阳县",
                "id": "430523"
            },
            {
                "city": "邵阳市",
                "name": "隆回县",
                "id": "430524"
            },
            {
                "city": "邵阳市",
                "name": "洞口县",
                "id": "430525"
            },
            {
                "city": "邵阳市",
                "name": "绥宁县",
                "id": "430527"
            },
            {
                "city": "邵阳市",
                "name": "新宁县",
                "id": "430528"
            },
            {
                "city": "邵阳市",
                "name": "城步苗族自治县",
                "id": "430529"
            },
            {
                "city": "邵阳市",
                "name": "武冈市",
                "id": "430581"
            }
        ],
        "430600": [
            {
                "city": "岳阳市",
                "name": "市辖区",
                "id": "430601"
            },
            {
                "city": "岳阳市",
                "name": "岳阳楼区",
                "id": "430602"
            },
            {
                "city": "岳阳市",
                "name": "云溪区",
                "id": "430603"
            },
            {
                "city": "岳阳市",
                "name": "君山区",
                "id": "430611"
            },
            {
                "city": "岳阳市",
                "name": "岳阳县",
                "id": "430621"
            },
            {
                "city": "岳阳市",
                "name": "华容县",
                "id": "430623"
            },
            {
                "city": "岳阳市",
                "name": "湘阴县",
                "id": "430624"
            },
            {
                "city": "岳阳市",
                "name": "平江县",
                "id": "430626"
            },
            {
                "city": "岳阳市",
                "name": "汨罗市",
                "id": "430681"
            },
            {
                "city": "岳阳市",
                "name": "临湘市",
                "id": "430682"
            }
        ],
        "430700": [
            {
                "city": "常德市",
                "name": "市辖区",
                "id": "430701"
            },
            {
                "city": "常德市",
                "name": "武陵区",
                "id": "430702"
            },
            {
                "city": "常德市",
                "name": "鼎城区",
                "id": "430703"
            },
            {
                "city": "常德市",
                "name": "安乡县",
                "id": "430721"
            },
            {
                "city": "常德市",
                "name": "汉寿县",
                "id": "430722"
            },
            {
                "city": "常德市",
                "name": "澧县",
                "id": "430723"
            },
            {
                "city": "常德市",
                "name": "临澧县",
                "id": "430724"
            },
            {
                "city": "常德市",
                "name": "桃源县",
                "id": "430725"
            },
            {
                "city": "常德市",
                "name": "石门县",
                "id": "430726"
            },
            {
                "city": "常德市",
                "name": "津市市",
                "id": "430781"
            }
        ],
        "430800": [
            {
                "city": "张家界市",
                "name": "市辖区",
                "id": "430801"
            },
            {
                "city": "张家界市",
                "name": "永定区",
                "id": "430802"
            },
            {
                "city": "张家界市",
                "name": "武陵源区",
                "id": "430811"
            },
            {
                "city": "张家界市",
                "name": "慈利县",
                "id": "430821"
            },
            {
                "city": "张家界市",
                "name": "桑植县",
                "id": "430822"
            }
        ],
        "430900": [
            {
                "city": "益阳市",
                "name": "市辖区",
                "id": "430901"
            },
            {
                "city": "益阳市",
                "name": "资阳区",
                "id": "430902"
            },
            {
                "city": "益阳市",
                "name": "赫山区",
                "id": "430903"
            },
            {
                "city": "益阳市",
                "name": "南县",
                "id": "430921"
            },
            {
                "city": "益阳市",
                "name": "桃江县",
                "id": "430922"
            },
            {
                "city": "益阳市",
                "name": "安化县",
                "id": "430923"
            },
            {
                "city": "益阳市",
                "name": "沅江市",
                "id": "430981"
            }
        ],
        "431000": [
            {
                "city": "郴州市",
                "name": "市辖区",
                "id": "431001"
            },
            {
                "city": "郴州市",
                "name": "北湖区",
                "id": "431002"
            },
            {
                "city": "郴州市",
                "name": "苏仙区",
                "id": "431003"
            },
            {
                "city": "郴州市",
                "name": "桂阳县",
                "id": "431021"
            },
            {
                "city": "郴州市",
                "name": "宜章县",
                "id": "431022"
            },
            {
                "city": "郴州市",
                "name": "永兴县",
                "id": "431023"
            },
            {
                "city": "郴州市",
                "name": "嘉禾县",
                "id": "431024"
            },
            {
                "city": "郴州市",
                "name": "临武县",
                "id": "431025"
            },
            {
                "city": "郴州市",
                "name": "汝城县",
                "id": "431026"
            },
            {
                "city": "郴州市",
                "name": "桂东县",
                "id": "431027"
            },
            {
                "city": "郴州市",
                "name": "安仁县",
                "id": "431028"
            },
            {
                "city": "郴州市",
                "name": "资兴市",
                "id": "431081"
            }
        ],
        "431100": [
            {
                "city": "永州市",
                "name": "市辖区",
                "id": "431101"
            },
            {
                "city": "永州市",
                "name": "零陵区",
                "id": "431102"
            },
            {
                "city": "永州市",
                "name": "冷水滩区",
                "id": "431103"
            },
            {
                "city": "永州市",
                "name": "祁阳县",
                "id": "431121"
            },
            {
                "city": "永州市",
                "name": "东安县",
                "id": "431122"
            },
            {
                "city": "永州市",
                "name": "双牌县",
                "id": "431123"
            },
            {
                "city": "永州市",
                "name": "道县",
                "id": "431124"
            },
            {
                "city": "永州市",
                "name": "江永县",
                "id": "431125"
            },
            {
                "city": "永州市",
                "name": "宁远县",
                "id": "431126"
            },
            {
                "city": "永州市",
                "name": "蓝山县",
                "id": "431127"
            },
            {
                "city": "永州市",
                "name": "新田县",
                "id": "431128"
            },
            {
                "city": "永州市",
                "name": "江华瑶族自治县",
                "id": "431129"
            }
        ],
        "431200": [
            {
                "city": "怀化市",
                "name": "市辖区",
                "id": "431201"
            },
            {
                "city": "怀化市",
                "name": "鹤城区",
                "id": "431202"
            },
            {
                "city": "怀化市",
                "name": "中方县",
                "id": "431221"
            },
            {
                "city": "怀化市",
                "name": "沅陵县",
                "id": "431222"
            },
            {
                "city": "怀化市",
                "name": "辰溪县",
                "id": "431223"
            },
            {
                "city": "怀化市",
                "name": "溆浦县",
                "id": "431224"
            },
            {
                "city": "怀化市",
                "name": "会同县",
                "id": "431225"
            },
            {
                "city": "怀化市",
                "name": "麻阳苗族自治县",
                "id": "431226"
            },
            {
                "city": "怀化市",
                "name": "新晃侗族自治县",
                "id": "431227"
            },
            {
                "city": "怀化市",
                "name": "芷江侗族自治县",
                "id": "431228"
            },
            {
                "city": "怀化市",
                "name": "靖州苗族侗族自治县",
                "id": "431229"
            },
            {
                "city": "怀化市",
                "name": "通道侗族自治县",
                "id": "431230"
            },
            {
                "city": "怀化市",
                "name": "洪江市",
                "id": "431281"
            }
        ],
        "431300": [
            {
                "city": "娄底市",
                "name": "市辖区",
                "id": "431301"
            },
            {
                "city": "娄底市",
                "name": "娄星区",
                "id": "431302"
            },
            {
                "city": "娄底市",
                "name": "双峰县",
                "id": "431321"
            },
            {
                "city": "娄底市",
                "name": "新化县",
                "id": "431322"
            },
            {
                "city": "娄底市",
                "name": "冷水江市",
                "id": "431381"
            },
            {
                "city": "娄底市",
                "name": "涟源市",
                "id": "431382"
            }
        ],
        "433100": [
            {
                "city": "湘西土家族苗族自治州",
                "name": "吉首市",
                "id": "433101"
            },
            {
                "city": "湘西土家族苗族自治州",
                "name": "泸溪县",
                "id": "433122"
            },
            {
                "city": "湘西土家族苗族自治州",
                "name": "凤凰县",
                "id": "433123"
            },
            {
                "city": "湘西土家族苗族自治州",
                "name": "花垣县",
                "id": "433124"
            },
            {
                "city": "湘西土家族苗族自治州",
                "name": "保靖县",
                "id": "433125"
            },
            {
                "city": "湘西土家族苗族自治州",
                "name": "古丈县",
                "id": "433126"
            },
            {
                "city": "湘西土家族苗族自治州",
                "name": "永顺县",
                "id": "433127"
            },
            {
                "city": "湘西土家族苗族自治州",
                "name": "龙山县",
                "id": "433130"
            }
        ],
        "440100": [
            {
                "city": "广州市",
                "name": "市辖区",
                "id": "440101"
            },
            {
                "city": "广州市",
                "name": "荔湾区",
                "id": "440103"
            },
            {
                "city": "广州市",
                "name": "越秀区",
                "id": "440104"
            },
            {
                "city": "广州市",
                "name": "海珠区",
                "id": "440105"
            },
            {
                "city": "广州市",
                "name": "天河区",
                "id": "440106"
            },
            {
                "city": "广州市",
                "name": "白云区",
                "id": "440111"
            },
            {
                "city": "广州市",
                "name": "黄埔区",
                "id": "440112"
            },
            {
                "city": "广州市",
                "name": "番禺区",
                "id": "440113"
            },
            {
                "city": "广州市",
                "name": "花都区",
                "id": "440114"
            },
            {
                "city": "广州市",
                "name": "南沙区",
                "id": "440115"
            },
            {
                "city": "广州市",
                "name": "从化区",
                "id": "440117"
            },
            {
                "city": "广州市",
                "name": "增城区",
                "id": "440118"
            }
        ],
        "440200": [
            {
                "city": "韶关市",
                "name": "市辖区",
                "id": "440201"
            },
            {
                "city": "韶关市",
                "name": "武江区",
                "id": "440203"
            },
            {
                "city": "韶关市",
                "name": "浈江区",
                "id": "440204"
            },
            {
                "city": "韶关市",
                "name": "曲江区",
                "id": "440205"
            },
            {
                "city": "韶关市",
                "name": "始兴县",
                "id": "440222"
            },
            {
                "city": "韶关市",
                "name": "仁化县",
                "id": "440224"
            },
            {
                "city": "韶关市",
                "name": "翁源县",
                "id": "440229"
            },
            {
                "city": "韶关市",
                "name": "乳源瑶族自治县",
                "id": "440232"
            },
            {
                "city": "韶关市",
                "name": "新丰县",
                "id": "440233"
            },
            {
                "city": "韶关市",
                "name": "乐昌市",
                "id": "440281"
            },
            {
                "city": "韶关市",
                "name": "南雄市",
                "id": "440282"
            }
        ],
        "440300": [
            {
                "city": "深圳市",
                "name": "市辖区",
                "id": "440301"
            },
            {
                "city": "深圳市",
                "name": "罗湖区",
                "id": "440303"
            },
            {
                "city": "深圳市",
                "name": "福田区",
                "id": "440304"
            },
            {
                "city": "深圳市",
                "name": "南山区",
                "id": "440305"
            },
            {
                "city": "深圳市",
                "name": "宝安区",
                "id": "440306"
            },
            {
                "city": "深圳市",
                "name": "龙岗区",
                "id": "440307"
            },
            {
                "city": "深圳市",
                "name": "盐田区",
                "id": "440308"
            }
        ],
        "440400": [
            {
                "city": "珠海市",
                "name": "市辖区",
                "id": "440401"
            },
            {
                "city": "珠海市",
                "name": "香洲区",
                "id": "440402"
            },
            {
                "city": "珠海市",
                "name": "斗门区",
                "id": "440403"
            },
            {
                "city": "珠海市",
                "name": "金湾区",
                "id": "440404"
            }
        ],
        "440500": [
            {
                "city": "汕头市",
                "name": "市辖区",
                "id": "440501"
            },
            {
                "city": "汕头市",
                "name": "龙湖区",
                "id": "440507"
            },
            {
                "city": "汕头市",
                "name": "金平区",
                "id": "440511"
            },
            {
                "city": "汕头市",
                "name": "濠江区",
                "id": "440512"
            },
            {
                "city": "汕头市",
                "name": "潮阳区",
                "id": "440513"
            },
            {
                "city": "汕头市",
                "name": "潮南区",
                "id": "440514"
            },
            {
                "city": "汕头市",
                "name": "澄海区",
                "id": "440515"
            },
            {
                "city": "汕头市",
                "name": "南澳县",
                "id": "440523"
            }
        ],
        "440600": [
            {
                "city": "佛山市",
                "name": "市辖区",
                "id": "440601"
            },
            {
                "city": "佛山市",
                "name": "禅城区",
                "id": "440604"
            },
            {
                "city": "佛山市",
                "name": "南海区",
                "id": "440605"
            },
            {
                "city": "佛山市",
                "name": "顺德区",
                "id": "440606"
            },
            {
                "city": "佛山市",
                "name": "三水区",
                "id": "440607"
            },
            {
                "city": "佛山市",
                "name": "高明区",
                "id": "440608"
            }
        ],
        "440700": [
            {
                "city": "江门市",
                "name": "市辖区",
                "id": "440701"
            },
            {
                "city": "江门市",
                "name": "蓬江区",
                "id": "440703"
            },
            {
                "city": "江门市",
                "name": "江海区",
                "id": "440704"
            },
            {
                "city": "江门市",
                "name": "新会区",
                "id": "440705"
            },
            {
                "city": "江门市",
                "name": "台山市",
                "id": "440781"
            },
            {
                "city": "江门市",
                "name": "开平市",
                "id": "440783"
            },
            {
                "city": "江门市",
                "name": "鹤山市",
                "id": "440784"
            },
            {
                "city": "江门市",
                "name": "恩平市",
                "id": "440785"
            }
        ],
        "440800": [
            {
                "city": "湛江市",
                "name": "市辖区",
                "id": "440801"
            },
            {
                "city": "湛江市",
                "name": "赤坎区",
                "id": "440802"
            },
            {
                "city": "湛江市",
                "name": "霞山区",
                "id": "440803"
            },
            {
                "city": "湛江市",
                "name": "坡头区",
                "id": "440804"
            },
            {
                "city": "湛江市",
                "name": "麻章区",
                "id": "440811"
            },
            {
                "city": "湛江市",
                "name": "遂溪县",
                "id": "440823"
            },
            {
                "city": "湛江市",
                "name": "徐闻县",
                "id": "440825"
            },
            {
                "city": "湛江市",
                "name": "廉江市",
                "id": "440881"
            },
            {
                "city": "湛江市",
                "name": "雷州市",
                "id": "440882"
            },
            {
                "city": "湛江市",
                "name": "吴川市",
                "id": "440883"
            }
        ],
        "440900": [
            {
                "city": "茂名市",
                "name": "市辖区",
                "id": "440901"
            },
            {
                "city": "茂名市",
                "name": "茂南区",
                "id": "440902"
            },
            {
                "city": "茂名市",
                "name": "电白区",
                "id": "440904"
            },
            {
                "city": "茂名市",
                "name": "高州市",
                "id": "440981"
            },
            {
                "city": "茂名市",
                "name": "化州市",
                "id": "440982"
            },
            {
                "city": "茂名市",
                "name": "信宜市",
                "id": "440983"
            }
        ],
        "441200": [
            {
                "city": "肇庆市",
                "name": "市辖区",
                "id": "441201"
            },
            {
                "city": "肇庆市",
                "name": "端州区",
                "id": "441202"
            },
            {
                "city": "肇庆市",
                "name": "鼎湖区",
                "id": "441203"
            },
            {
                "city": "肇庆市",
                "name": "高要区",
                "id": "441204"
            },
            {
                "city": "肇庆市",
                "name": "广宁县",
                "id": "441223"
            },
            {
                "city": "肇庆市",
                "name": "怀集县",
                "id": "441224"
            },
            {
                "city": "肇庆市",
                "name": "封开县",
                "id": "441225"
            },
            {
                "city": "肇庆市",
                "name": "德庆县",
                "id": "441226"
            },
            {
                "city": "肇庆市",
                "name": "四会市",
                "id": "441284"
            }
        ],
        "441300": [
            {
                "city": "惠州市",
                "name": "市辖区",
                "id": "441301"
            },
            {
                "city": "惠州市",
                "name": "惠城区",
                "id": "441302"
            },
            {
                "city": "惠州市",
                "name": "惠阳区",
                "id": "441303"
            },
            {
                "city": "惠州市",
                "name": "博罗县",
                "id": "441322"
            },
            {
                "city": "惠州市",
                "name": "惠东县",
                "id": "441323"
            },
            {
                "city": "惠州市",
                "name": "龙门县",
                "id": "441324"
            }
        ],
        "441400": [
            {
                "city": "梅州市",
                "name": "市辖区",
                "id": "441401"
            },
            {
                "city": "梅州市",
                "name": "梅江区",
                "id": "441402"
            },
            {
                "city": "梅州市",
                "name": "梅县区",
                "id": "441403"
            },
            {
                "city": "梅州市",
                "name": "大埔县",
                "id": "441422"
            },
            {
                "city": "梅州市",
                "name": "丰顺县",
                "id": "441423"
            },
            {
                "city": "梅州市",
                "name": "五华县",
                "id": "441424"
            },
            {
                "city": "梅州市",
                "name": "平远县",
                "id": "441426"
            },
            {
                "city": "梅州市",
                "name": "蕉岭县",
                "id": "441427"
            },
            {
                "city": "梅州市",
                "name": "兴宁市",
                "id": "441481"
            }
        ],
        "441500": [
            {
                "city": "汕尾市",
                "name": "市辖区",
                "id": "441501"
            },
            {
                "city": "汕尾市",
                "name": "城区",
                "id": "441502"
            },
            {
                "city": "汕尾市",
                "name": "海丰县",
                "id": "441521"
            },
            {
                "city": "汕尾市",
                "name": "陆河县",
                "id": "441523"
            },
            {
                "city": "汕尾市",
                "name": "陆丰市",
                "id": "441581"
            }
        ],
        "441600": [
            {
                "city": "河源市",
                "name": "市辖区",
                "id": "441601"
            },
            {
                "city": "河源市",
                "name": "源城区",
                "id": "441602"
            },
            {
                "city": "河源市",
                "name": "紫金县",
                "id": "441621"
            },
            {
                "city": "河源市",
                "name": "龙川县",
                "id": "441622"
            },
            {
                "city": "河源市",
                "name": "连平县",
                "id": "441623"
            },
            {
                "city": "河源市",
                "name": "和平县",
                "id": "441624"
            },
            {
                "city": "河源市",
                "name": "东源县",
                "id": "441625"
            }
        ],
        "441700": [
            {
                "city": "阳江市",
                "name": "市辖区",
                "id": "441701"
            },
            {
                "city": "阳江市",
                "name": "江城区",
                "id": "441702"
            },
            {
                "city": "阳江市",
                "name": "阳东区",
                "id": "441704"
            },
            {
                "city": "阳江市",
                "name": "阳西县",
                "id": "441721"
            },
            {
                "city": "阳江市",
                "name": "阳春市",
                "id": "441781"
            }
        ],
        "441800": [
            {
                "city": "清远市",
                "name": "市辖区",
                "id": "441801"
            },
            {
                "city": "清远市",
                "name": "清城区",
                "id": "441802"
            },
            {
                "city": "清远市",
                "name": "清新区",
                "id": "441803"
            },
            {
                "city": "清远市",
                "name": "佛冈县",
                "id": "441821"
            },
            {
                "city": "清远市",
                "name": "阳山县",
                "id": "441823"
            },
            {
                "city": "清远市",
                "name": "连山壮族瑶族自治县",
                "id": "441825"
            },
            {
                "city": "清远市",
                "name": "连南瑶族自治县",
                "id": "441826"
            },
            {
                "city": "清远市",
                "name": "英德市",
                "id": "441881"
            },
            {
                "city": "清远市",
                "name": "连州市",
                "id": "441882"
            }
        ],
        "441900": [],
        "442000": [],
        "445100": [
            {
                "city": "潮州市",
                "name": "市辖区",
                "id": "445101"
            },
            {
                "city": "潮州市",
                "name": "湘桥区",
                "id": "445102"
            },
            {
                "city": "潮州市",
                "name": "潮安区",
                "id": "445103"
            },
            {
                "city": "潮州市",
                "name": "饶平县",
                "id": "445122"
            }
        ],
        "445200": [
            {
                "city": "揭阳市",
                "name": "市辖区",
                "id": "445201"
            },
            {
                "city": "揭阳市",
                "name": "榕城区",
                "id": "445202"
            },
            {
                "city": "揭阳市",
                "name": "揭东区",
                "id": "445203"
            },
            {
                "city": "揭阳市",
                "name": "揭西县",
                "id": "445222"
            },
            {
                "city": "揭阳市",
                "name": "惠来县",
                "id": "445224"
            },
            {
                "city": "揭阳市",
                "name": "普宁市",
                "id": "445281"
            }
        ],
        "445300": [
            {
                "city": "云浮市",
                "name": "市辖区",
                "id": "445301"
            },
            {
                "city": "云浮市",
                "name": "云城区",
                "id": "445302"
            },
            {
                "city": "云浮市",
                "name": "云安区",
                "id": "445303"
            },
            {
                "city": "云浮市",
                "name": "新兴县",
                "id": "445321"
            },
            {
                "city": "云浮市",
                "name": "郁南县",
                "id": "445322"
            },
            {
                "city": "云浮市",
                "name": "罗定市",
                "id": "445381"
            }
        ],
        "450100": [
            {
                "city": "南宁市",
                "name": "市辖区",
                "id": "450101"
            },
            {
                "city": "南宁市",
                "name": "兴宁区",
                "id": "450102"
            },
            {
                "city": "南宁市",
                "name": "青秀区",
                "id": "450103"
            },
            {
                "city": "南宁市",
                "name": "江南区",
                "id": "450105"
            },
            {
                "city": "南宁市",
                "name": "西乡塘区",
                "id": "450107"
            },
            {
                "city": "南宁市",
                "name": "良庆区",
                "id": "450108"
            },
            {
                "city": "南宁市",
                "name": "邕宁区",
                "id": "450109"
            },
            {
                "city": "南宁市",
                "name": "武鸣区",
                "id": "450110"
            },
            {
                "city": "南宁市",
                "name": "隆安县",
                "id": "450123"
            },
            {
                "city": "南宁市",
                "name": "马山县",
                "id": "450124"
            },
            {
                "city": "南宁市",
                "name": "上林县",
                "id": "450125"
            },
            {
                "city": "南宁市",
                "name": "宾阳县",
                "id": "450126"
            },
            {
                "city": "南宁市",
                "name": "横县",
                "id": "450127"
            }
        ],
        "450200": [
            {
                "city": "柳州市",
                "name": "市辖区",
                "id": "450201"
            },
            {
                "city": "柳州市",
                "name": "城中区",
                "id": "450202"
            },
            {
                "city": "柳州市",
                "name": "鱼峰区",
                "id": "450203"
            },
            {
                "city": "柳州市",
                "name": "柳南区",
                "id": "450204"
            },
            {
                "city": "柳州市",
                "name": "柳北区",
                "id": "450205"
            },
            {
                "city": "柳州市",
                "name": "柳江区",
                "id": "450206"
            },
            {
                "city": "柳州市",
                "name": "柳城县",
                "id": "450222"
            },
            {
                "city": "柳州市",
                "name": "鹿寨县",
                "id": "450223"
            },
            {
                "city": "柳州市",
                "name": "融安县",
                "id": "450224"
            },
            {
                "city": "柳州市",
                "name": "融水苗族自治县",
                "id": "450225"
            },
            {
                "city": "柳州市",
                "name": "三江侗族自治县",
                "id": "450226"
            }
        ],
        "450300": [
            {
                "city": "桂林市",
                "name": "市辖区",
                "id": "450301"
            },
            {
                "city": "桂林市",
                "name": "秀峰区",
                "id": "450302"
            },
            {
                "city": "桂林市",
                "name": "叠彩区",
                "id": "450303"
            },
            {
                "city": "桂林市",
                "name": "象山区",
                "id": "450304"
            },
            {
                "city": "桂林市",
                "name": "七星区",
                "id": "450305"
            },
            {
                "city": "桂林市",
                "name": "雁山区",
                "id": "450311"
            },
            {
                "city": "桂林市",
                "name": "临桂区",
                "id": "450312"
            },
            {
                "city": "桂林市",
                "name": "阳朔县",
                "id": "450321"
            },
            {
                "city": "桂林市",
                "name": "灵川县",
                "id": "450323"
            },
            {
                "city": "桂林市",
                "name": "全州县",
                "id": "450324"
            },
            {
                "city": "桂林市",
                "name": "兴安县",
                "id": "450325"
            },
            {
                "city": "桂林市",
                "name": "永福县",
                "id": "450326"
            },
            {
                "city": "桂林市",
                "name": "灌阳县",
                "id": "450327"
            },
            {
                "city": "桂林市",
                "name": "龙胜各族自治县",
                "id": "450328"
            },
            {
                "city": "桂林市",
                "name": "资源县",
                "id": "450329"
            },
            {
                "city": "桂林市",
                "name": "平乐县",
                "id": "450330"
            },
            {
                "city": "桂林市",
                "name": "荔浦县",
                "id": "450331"
            },
            {
                "city": "桂林市",
                "name": "恭城瑶族自治县",
                "id": "450332"
            }
        ],
        "450400": [
            {
                "city": "梧州市",
                "name": "市辖区",
                "id": "450401"
            },
            {
                "city": "梧州市",
                "name": "万秀区",
                "id": "450403"
            },
            {
                "city": "梧州市",
                "name": "长洲区",
                "id": "450405"
            },
            {
                "city": "梧州市",
                "name": "龙圩区",
                "id": "450406"
            },
            {
                "city": "梧州市",
                "name": "苍梧县",
                "id": "450421"
            },
            {
                "city": "梧州市",
                "name": "藤县",
                "id": "450422"
            },
            {
                "city": "梧州市",
                "name": "蒙山县",
                "id": "450423"
            },
            {
                "city": "梧州市",
                "name": "岑溪市",
                "id": "450481"
            }
        ],
        "450500": [
            {
                "city": "北海市",
                "name": "市辖区",
                "id": "450501"
            },
            {
                "city": "北海市",
                "name": "海城区",
                "id": "450502"
            },
            {
                "city": "北海市",
                "name": "银海区",
                "id": "450503"
            },
            {
                "city": "北海市",
                "name": "铁山港区",
                "id": "450512"
            },
            {
                "city": "北海市",
                "name": "合浦县",
                "id": "450521"
            }
        ],
        "450600": [
            {
                "city": "防城港市",
                "name": "市辖区",
                "id": "450601"
            },
            {
                "city": "防城港市",
                "name": "港口区",
                "id": "450602"
            },
            {
                "city": "防城港市",
                "name": "防城区",
                "id": "450603"
            },
            {
                "city": "防城港市",
                "name": "上思县",
                "id": "450621"
            },
            {
                "city": "防城港市",
                "name": "东兴市",
                "id": "450681"
            }
        ],
        "450700": [
            {
                "city": "钦州市",
                "name": "市辖区",
                "id": "450701"
            },
            {
                "city": "钦州市",
                "name": "钦南区",
                "id": "450702"
            },
            {
                "city": "钦州市",
                "name": "钦北区",
                "id": "450703"
            },
            {
                "city": "钦州市",
                "name": "灵山县",
                "id": "450721"
            },
            {
                "city": "钦州市",
                "name": "浦北县",
                "id": "450722"
            }
        ],
        "450800": [
            {
                "city": "贵港市",
                "name": "市辖区",
                "id": "450801"
            },
            {
                "city": "贵港市",
                "name": "港北区",
                "id": "450802"
            },
            {
                "city": "贵港市",
                "name": "港南区",
                "id": "450803"
            },
            {
                "city": "贵港市",
                "name": "覃塘区",
                "id": "450804"
            },
            {
                "city": "贵港市",
                "name": "平南县",
                "id": "450821"
            },
            {
                "city": "贵港市",
                "name": "桂平市",
                "id": "450881"
            }
        ],
        "450900": [
            {
                "city": "玉林市",
                "name": "市辖区",
                "id": "450901"
            },
            {
                "city": "玉林市",
                "name": "玉州区",
                "id": "450902"
            },
            {
                "city": "玉林市",
                "name": "福绵区",
                "id": "450903"
            },
            {
                "city": "玉林市",
                "name": "容县",
                "id": "450921"
            },
            {
                "city": "玉林市",
                "name": "陆川县",
                "id": "450922"
            },
            {
                "city": "玉林市",
                "name": "博白县",
                "id": "450923"
            },
            {
                "city": "玉林市",
                "name": "兴业县",
                "id": "450924"
            },
            {
                "city": "玉林市",
                "name": "北流市",
                "id": "450981"
            }
        ],
        "451000": [
            {
                "city": "百色市",
                "name": "市辖区",
                "id": "451001"
            },
            {
                "city": "百色市",
                "name": "右江区",
                "id": "451002"
            },
            {
                "city": "百色市",
                "name": "田阳县",
                "id": "451021"
            },
            {
                "city": "百色市",
                "name": "田东县",
                "id": "451022"
            },
            {
                "city": "百色市",
                "name": "平果县",
                "id": "451023"
            },
            {
                "city": "百色市",
                "name": "德保县",
                "id": "451024"
            },
            {
                "city": "百色市",
                "name": "那坡县",
                "id": "451026"
            },
            {
                "city": "百色市",
                "name": "凌云县",
                "id": "451027"
            },
            {
                "city": "百色市",
                "name": "乐业县",
                "id": "451028"
            },
            {
                "city": "百色市",
                "name": "田林县",
                "id": "451029"
            },
            {
                "city": "百色市",
                "name": "西林县",
                "id": "451030"
            },
            {
                "city": "百色市",
                "name": "隆林各族自治县",
                "id": "451031"
            },
            {
                "city": "百色市",
                "name": "靖西市",
                "id": "451081"
            }
        ],
        "451100": [
            {
                "city": "贺州市",
                "name": "市辖区",
                "id": "451101"
            },
            {
                "city": "贺州市",
                "name": "八步区",
                "id": "451102"
            },
            {
                "city": "贺州市",
                "name": "平桂区",
                "id": "451103"
            },
            {
                "city": "贺州市",
                "name": "昭平县",
                "id": "451121"
            },
            {
                "city": "贺州市",
                "name": "钟山县",
                "id": "451122"
            },
            {
                "city": "贺州市",
                "name": "富川瑶族自治县",
                "id": "451123"
            }
        ],
        "451200": [
            {
                "city": "河池市",
                "name": "市辖区",
                "id": "451201"
            },
            {
                "city": "河池市",
                "name": "金城江区",
                "id": "451202"
            },
            {
                "city": "河池市",
                "name": "南丹县",
                "id": "451221"
            },
            {
                "city": "河池市",
                "name": "天峨县",
                "id": "451222"
            },
            {
                "city": "河池市",
                "name": "凤山县",
                "id": "451223"
            },
            {
                "city": "河池市",
                "name": "东兰县",
                "id": "451224"
            },
            {
                "city": "河池市",
                "name": "罗城仫佬族自治县",
                "id": "451225"
            },
            {
                "city": "河池市",
                "name": "环江毛南族自治县",
                "id": "451226"
            },
            {
                "city": "河池市",
                "name": "巴马瑶族自治县",
                "id": "451227"
            },
            {
                "city": "河池市",
                "name": "都安瑶族自治县",
                "id": "451228"
            },
            {
                "city": "河池市",
                "name": "大化瑶族自治县",
                "id": "451229"
            },
            {
                "city": "河池市",
                "name": "宜州市",
                "id": "451281"
            }
        ],
        "451300": [
            {
                "city": "来宾市",
                "name": "市辖区",
                "id": "451301"
            },
            {
                "city": "来宾市",
                "name": "兴宾区",
                "id": "451302"
            },
            {
                "city": "来宾市",
                "name": "忻城县",
                "id": "451321"
            },
            {
                "city": "来宾市",
                "name": "象州县",
                "id": "451322"
            },
            {
                "city": "来宾市",
                "name": "武宣县",
                "id": "451323"
            },
            {
                "city": "来宾市",
                "name": "金秀瑶族自治县",
                "id": "451324"
            },
            {
                "city": "来宾市",
                "name": "合山市",
                "id": "451381"
            }
        ],
        "451400": [
            {
                "city": "崇左市",
                "name": "市辖区",
                "id": "451401"
            },
            {
                "city": "崇左市",
                "name": "江州区",
                "id": "451402"
            },
            {
                "city": "崇左市",
                "name": "扶绥县",
                "id": "451421"
            },
            {
                "city": "崇左市",
                "name": "宁明县",
                "id": "451422"
            },
            {
                "city": "崇左市",
                "name": "龙州县",
                "id": "451423"
            },
            {
                "city": "崇左市",
                "name": "大新县",
                "id": "451424"
            },
            {
                "city": "崇左市",
                "name": "天等县",
                "id": "451425"
            },
            {
                "city": "崇左市",
                "name": "凭祥市",
                "id": "451481"
            }
        ],
        "460100": [
            {
                "city": "海口市",
                "name": "市辖区",
                "id": "460101"
            },
            {
                "city": "海口市",
                "name": "秀英区",
                "id": "460105"
            },
            {
                "city": "海口市",
                "name": "龙华区",
                "id": "460106"
            },
            {
                "city": "海口市",
                "name": "琼山区",
                "id": "460107"
            },
            {
                "city": "海口市",
                "name": "美兰区",
                "id": "460108"
            }
        ],
        "460200": [
            {
                "city": "三亚市",
                "name": "市辖区",
                "id": "460201"
            },
            {
                "city": "三亚市",
                "name": "海棠区",
                "id": "460202"
            },
            {
                "city": "三亚市",
                "name": "吉阳区",
                "id": "460203"
            },
            {
                "city": "三亚市",
                "name": "天涯区",
                "id": "460204"
            },
            {
                "city": "三亚市",
                "name": "崖州区",
                "id": "460205"
            }
        ],
        "460300": [],
        "460400": [],
        "469000": [
            {
                "city": "省直辖县级行政区划",
                "name": "五指山市",
                "id": "469001"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "琼海市",
                "id": "469002"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "文昌市",
                "id": "469005"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "万宁市",
                "id": "469006"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "东方市",
                "id": "469007"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "定安县",
                "id": "469021"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "屯昌县",
                "id": "469022"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "澄迈县",
                "id": "469023"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "临高县",
                "id": "469024"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "白沙黎族自治县",
                "id": "469025"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "昌江黎族自治县",
                "id": "469026"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "乐东黎族自治县",
                "id": "469027"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "陵水黎族自治县",
                "id": "469028"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "保亭黎族苗族自治县",
                "id": "469029"
            },
            {
                "city": "省直辖县级行政区划",
                "name": "琼中黎族苗族自治县",
                "id": "469030"
            }
        ],
        "500100": [
            {
                "city": "市辖区",
                "name": "万州区",
                "id": "500101"
            },
            {
                "city": "市辖区",
                "name": "涪陵区",
                "id": "500102"
            },
            {
                "city": "市辖区",
                "name": "渝中区",
                "id": "500103"
            },
            {
                "city": "市辖区",
                "name": "大渡口区",
                "id": "500104"
            },
            {
                "city": "市辖区",
                "name": "江北区",
                "id": "500105"
            },
            {
                "city": "市辖区",
                "name": "沙坪坝区",
                "id": "500106"
            },
            {
                "city": "市辖区",
                "name": "九龙坡区",
                "id": "500107"
            },
            {
                "city": "市辖区",
                "name": "南岸区",
                "id": "500108"
            },
            {
                "city": "市辖区",
                "name": "北碚区",
                "id": "500109"
            },
            {
                "city": "市辖区",
                "name": "綦江区",
                "id": "500110"
            },
            {
                "city": "市辖区",
                "name": "大足区",
                "id": "500111"
            },
            {
                "city": "市辖区",
                "name": "渝北区",
                "id": "500112"
            },
            {
                "city": "市辖区",
                "name": "巴南区",
                "id": "500113"
            },
            {
                "city": "市辖区",
                "name": "黔江区",
                "id": "500114"
            },
            {
                "city": "市辖区",
                "name": "长寿区",
                "id": "500115"
            },
            {
                "city": "市辖区",
                "name": "江津区",
                "id": "500116"
            },
            {
                "city": "市辖区",
                "name": "合川区",
                "id": "500117"
            },
            {
                "city": "市辖区",
                "name": "永川区",
                "id": "500118"
            },
            {
                "city": "市辖区",
                "name": "南川区",
                "id": "500119"
            },
            {
                "city": "市辖区",
                "name": "璧山区",
                "id": "500120"
            },
            {
                "city": "市辖区",
                "name": "铜梁区",
                "id": "500151"
            },
            {
                "city": "市辖区",
                "name": "潼南区",
                "id": "500152"
            },
            {
                "city": "市辖区",
                "name": "荣昌区",
                "id": "500153"
            },
            {
                "city": "市辖区",
                "name": "开州区",
                "id": "500154"
            }
        ],
        "500200": [
            {
                "city": "县",
                "name": "梁平县",
                "id": "500228"
            },
            {
                "city": "县",
                "name": "城口县",
                "id": "500229"
            },
            {
                "city": "县",
                "name": "丰都县",
                "id": "500230"
            },
            {
                "city": "县",
                "name": "垫江县",
                "id": "500231"
            },
            {
                "city": "县",
                "name": "武隆县",
                "id": "500232"
            },
            {
                "city": "县",
                "name": "忠县",
                "id": "500233"
            },
            {
                "city": "县",
                "name": "云阳县",
                "id": "500235"
            },
            {
                "city": "县",
                "name": "奉节县",
                "id": "500236"
            },
            {
                "city": "县",
                "name": "巫山县",
                "id": "500237"
            },
            {
                "city": "县",
                "name": "巫溪县",
                "id": "500238"
            },
            {
                "city": "县",
                "name": "石柱土家族自治县",
                "id": "500240"
            },
            {
                "city": "县",
                "name": "秀山土家族苗族自治县",
                "id": "500241"
            },
            {
                "city": "县",
                "name": "酉阳土家族苗族自治县",
                "id": "500242"
            },
            {
                "city": "县",
                "name": "彭水苗族土家族自治县",
                "id": "500243"
            }
        ],
        "510100": [
            {
                "city": "成都市",
                "name": "市辖区",
                "id": "510101"
            },
            {
                "city": "成都市",
                "name": "锦江区",
                "id": "510104"
            },
            {
                "city": "成都市",
                "name": "青羊区",
                "id": "510105"
            },
            {
                "city": "成都市",
                "name": "金牛区",
                "id": "510106"
            },
            {
                "city": "成都市",
                "name": "武侯区",
                "id": "510107"
            },
            {
                "city": "成都市",
                "name": "成华区",
                "id": "510108"
            },
            {
                "city": "成都市",
                "name": "龙泉驿区",
                "id": "510112"
            },
            {
                "city": "成都市",
                "name": "青白江区",
                "id": "510113"
            },
            {
                "city": "成都市",
                "name": "新都区",
                "id": "510114"
            },
            {
                "city": "成都市",
                "name": "温江区",
                "id": "510115"
            },
            {
                "city": "成都市",
                "name": "双流区",
                "id": "510116"
            },
            {
                "city": "成都市",
                "name": "金堂县",
                "id": "510121"
            },
            {
                "city": "成都市",
                "name": "郫县",
                "id": "510124"
            },
            {
                "city": "成都市",
                "name": "大邑县",
                "id": "510129"
            },
            {
                "city": "成都市",
                "name": "蒲江县",
                "id": "510131"
            },
            {
                "city": "成都市",
                "name": "新津县",
                "id": "510132"
            },
            {
                "city": "成都市",
                "name": "都江堰市",
                "id": "510181"
            },
            {
                "city": "成都市",
                "name": "彭州市",
                "id": "510182"
            },
            {
                "city": "成都市",
                "name": "邛崃市",
                "id": "510183"
            },
            {
                "city": "成都市",
                "name": "崇州市",
                "id": "510184"
            },
            {
                "city": "成都市",
                "name": "简阳市",
                "id": "510185"
            }
        ],
        "510300": [
            {
                "city": "自贡市",
                "name": "市辖区",
                "id": "510301"
            },
            {
                "city": "自贡市",
                "name": "自流井区",
                "id": "510302"
            },
            {
                "city": "自贡市",
                "name": "贡井区",
                "id": "510303"
            },
            {
                "city": "自贡市",
                "name": "大安区",
                "id": "510304"
            },
            {
                "city": "自贡市",
                "name": "沿滩区",
                "id": "510311"
            },
            {
                "city": "自贡市",
                "name": "荣县",
                "id": "510321"
            },
            {
                "city": "自贡市",
                "name": "富顺县",
                "id": "510322"
            }
        ],
        "510400": [
            {
                "city": "攀枝花市",
                "name": "市辖区",
                "id": "510401"
            },
            {
                "city": "攀枝花市",
                "name": "东区",
                "id": "510402"
            },
            {
                "city": "攀枝花市",
                "name": "西区",
                "id": "510403"
            },
            {
                "city": "攀枝花市",
                "name": "仁和区",
                "id": "510411"
            },
            {
                "city": "攀枝花市",
                "name": "米易县",
                "id": "510421"
            },
            {
                "city": "攀枝花市",
                "name": "盐边县",
                "id": "510422"
            }
        ],
        "510500": [
            {
                "city": "泸州市",
                "name": "市辖区",
                "id": "510501"
            },
            {
                "city": "泸州市",
                "name": "江阳区",
                "id": "510502"
            },
            {
                "city": "泸州市",
                "name": "纳溪区",
                "id": "510503"
            },
            {
                "city": "泸州市",
                "name": "龙马潭区",
                "id": "510504"
            },
            {
                "city": "泸州市",
                "name": "泸县",
                "id": "510521"
            },
            {
                "city": "泸州市",
                "name": "合江县",
                "id": "510522"
            },
            {
                "city": "泸州市",
                "name": "叙永县",
                "id": "510524"
            },
            {
                "city": "泸州市",
                "name": "古蔺县",
                "id": "510525"
            }
        ],
        "510600": [
            {
                "city": "德阳市",
                "name": "市辖区",
                "id": "510601"
            },
            {
                "city": "德阳市",
                "name": "旌阳区",
                "id": "510603"
            },
            {
                "city": "德阳市",
                "name": "中江县",
                "id": "510623"
            },
            {
                "city": "德阳市",
                "name": "罗江县",
                "id": "510626"
            },
            {
                "city": "德阳市",
                "name": "广汉市",
                "id": "510681"
            },
            {
                "city": "德阳市",
                "name": "什邡市",
                "id": "510682"
            },
            {
                "city": "德阳市",
                "name": "绵竹市",
                "id": "510683"
            }
        ],
        "510700": [
            {
                "city": "绵阳市",
                "name": "市辖区",
                "id": "510701"
            },
            {
                "city": "绵阳市",
                "name": "涪城区",
                "id": "510703"
            },
            {
                "city": "绵阳市",
                "name": "游仙区",
                "id": "510704"
            },
            {
                "city": "绵阳市",
                "name": "安州区",
                "id": "510705"
            },
            {
                "city": "绵阳市",
                "name": "三台县",
                "id": "510722"
            },
            {
                "city": "绵阳市",
                "name": "盐亭县",
                "id": "510723"
            },
            {
                "city": "绵阳市",
                "name": "梓潼县",
                "id": "510725"
            },
            {
                "city": "绵阳市",
                "name": "北川羌族自治县",
                "id": "510726"
            },
            {
                "city": "绵阳市",
                "name": "平武县",
                "id": "510727"
            },
            {
                "city": "绵阳市",
                "name": "江油市",
                "id": "510781"
            }
        ],
        "510800": [
            {
                "city": "广元市",
                "name": "市辖区",
                "id": "510801"
            },
            {
                "city": "广元市",
                "name": "利州区",
                "id": "510802"
            },
            {
                "city": "广元市",
                "name": "昭化区",
                "id": "510811"
            },
            {
                "city": "广元市",
                "name": "朝天区",
                "id": "510812"
            },
            {
                "city": "广元市",
                "name": "旺苍县",
                "id": "510821"
            },
            {
                "city": "广元市",
                "name": "青川县",
                "id": "510822"
            },
            {
                "city": "广元市",
                "name": "剑阁县",
                "id": "510823"
            },
            {
                "city": "广元市",
                "name": "苍溪县",
                "id": "510824"
            }
        ],
        "510900": [
            {
                "city": "遂宁市",
                "name": "市辖区",
                "id": "510901"
            },
            {
                "city": "遂宁市",
                "name": "船山区",
                "id": "510903"
            },
            {
                "city": "遂宁市",
                "name": "安居区",
                "id": "510904"
            },
            {
                "city": "遂宁市",
                "name": "蓬溪县",
                "id": "510921"
            },
            {
                "city": "遂宁市",
                "name": "射洪县",
                "id": "510922"
            },
            {
                "city": "遂宁市",
                "name": "大英县",
                "id": "510923"
            }
        ],
        "511000": [
            {
                "city": "内江市",
                "name": "市辖区",
                "id": "511001"
            },
            {
                "city": "内江市",
                "name": "市中区",
                "id": "511002"
            },
            {
                "city": "内江市",
                "name": "东兴区",
                "id": "511011"
            },
            {
                "city": "内江市",
                "name": "威远县",
                "id": "511024"
            },
            {
                "city": "内江市",
                "name": "资中县",
                "id": "511025"
            },
            {
                "city": "内江市",
                "name": "隆昌县",
                "id": "511028"
            }
        ],
        "511100": [
            {
                "city": "乐山市",
                "name": "市辖区",
                "id": "511101"
            },
            {
                "city": "乐山市",
                "name": "市中区",
                "id": "511102"
            },
            {
                "city": "乐山市",
                "name": "沙湾区",
                "id": "511111"
            },
            {
                "city": "乐山市",
                "name": "五通桥区",
                "id": "511112"
            },
            {
                "city": "乐山市",
                "name": "金口河区",
                "id": "511113"
            },
            {
                "city": "乐山市",
                "name": "犍为县",
                "id": "511123"
            },
            {
                "city": "乐山市",
                "name": "井研县",
                "id": "511124"
            },
            {
                "city": "乐山市",
                "name": "夹江县",
                "id": "511126"
            },
            {
                "city": "乐山市",
                "name": "沐川县",
                "id": "511129"
            },
            {
                "city": "乐山市",
                "name": "峨边彝族自治县",
                "id": "511132"
            },
            {
                "city": "乐山市",
                "name": "马边彝族自治县",
                "id": "511133"
            },
            {
                "city": "乐山市",
                "name": "峨眉山市",
                "id": "511181"
            }
        ],
        "511300": [
            {
                "city": "南充市",
                "name": "市辖区",
                "id": "511301"
            },
            {
                "city": "南充市",
                "name": "顺庆区",
                "id": "511302"
            },
            {
                "city": "南充市",
                "name": "高坪区",
                "id": "511303"
            },
            {
                "city": "南充市",
                "name": "嘉陵区",
                "id": "511304"
            },
            {
                "city": "南充市",
                "name": "南部县",
                "id": "511321"
            },
            {
                "city": "南充市",
                "name": "营山县",
                "id": "511322"
            },
            {
                "city": "南充市",
                "name": "蓬安县",
                "id": "511323"
            },
            {
                "city": "南充市",
                "name": "仪陇县",
                "id": "511324"
            },
            {
                "city": "南充市",
                "name": "西充县",
                "id": "511325"
            },
            {
                "city": "南充市",
                "name": "阆中市",
                "id": "511381"
            }
        ],
        "511400": [
            {
                "city": "眉山市",
                "name": "市辖区",
                "id": "511401"
            },
            {
                "city": "眉山市",
                "name": "东坡区",
                "id": "511402"
            },
            {
                "city": "眉山市",
                "name": "彭山区",
                "id": "511403"
            },
            {
                "city": "眉山市",
                "name": "仁寿县",
                "id": "511421"
            },
            {
                "city": "眉山市",
                "name": "洪雅县",
                "id": "511423"
            },
            {
                "city": "眉山市",
                "name": "丹棱县",
                "id": "511424"
            },
            {
                "city": "眉山市",
                "name": "青神县",
                "id": "511425"
            }
        ],
        "511500": [
            {
                "city": "宜宾市",
                "name": "市辖区",
                "id": "511501"
            },
            {
                "city": "宜宾市",
                "name": "翠屏区",
                "id": "511502"
            },
            {
                "city": "宜宾市",
                "name": "南溪区",
                "id": "511503"
            },
            {
                "city": "宜宾市",
                "name": "宜宾县",
                "id": "511521"
            },
            {
                "city": "宜宾市",
                "name": "江安县",
                "id": "511523"
            },
            {
                "city": "宜宾市",
                "name": "长宁县",
                "id": "511524"
            },
            {
                "city": "宜宾市",
                "name": "高县",
                "id": "511525"
            },
            {
                "city": "宜宾市",
                "name": "珙县",
                "id": "511526"
            },
            {
                "city": "宜宾市",
                "name": "筠连县",
                "id": "511527"
            },
            {
                "city": "宜宾市",
                "name": "兴文县",
                "id": "511528"
            },
            {
                "city": "宜宾市",
                "name": "屏山县",
                "id": "511529"
            }
        ],
        "511600": [
            {
                "city": "广安市",
                "name": "市辖区",
                "id": "511601"
            },
            {
                "city": "广安市",
                "name": "广安区",
                "id": "511602"
            },
            {
                "city": "广安市",
                "name": "前锋区",
                "id": "511603"
            },
            {
                "city": "广安市",
                "name": "岳池县",
                "id": "511621"
            },
            {
                "city": "广安市",
                "name": "武胜县",
                "id": "511622"
            },
            {
                "city": "广安市",
                "name": "邻水县",
                "id": "511623"
            },
            {
                "city": "广安市",
                "name": "华蓥市",
                "id": "511681"
            }
        ],
        "511700": [
            {
                "city": "达州市",
                "name": "市辖区",
                "id": "511701"
            },
            {
                "city": "达州市",
                "name": "通川区",
                "id": "511702"
            },
            {
                "city": "达州市",
                "name": "达川区",
                "id": "511703"
            },
            {
                "city": "达州市",
                "name": "宣汉县",
                "id": "511722"
            },
            {
                "city": "达州市",
                "name": "开江县",
                "id": "511723"
            },
            {
                "city": "达州市",
                "name": "大竹县",
                "id": "511724"
            },
            {
                "city": "达州市",
                "name": "渠县",
                "id": "511725"
            },
            {
                "city": "达州市",
                "name": "万源市",
                "id": "511781"
            }
        ],
        "511800": [
            {
                "city": "雅安市",
                "name": "市辖区",
                "id": "511801"
            },
            {
                "city": "雅安市",
                "name": "雨城区",
                "id": "511802"
            },
            {
                "city": "雅安市",
                "name": "名山区",
                "id": "511803"
            },
            {
                "city": "雅安市",
                "name": "荥经县",
                "id": "511822"
            },
            {
                "city": "雅安市",
                "name": "汉源县",
                "id": "511823"
            },
            {
                "city": "雅安市",
                "name": "石棉县",
                "id": "511824"
            },
            {
                "city": "雅安市",
                "name": "天全县",
                "id": "511825"
            },
            {
                "city": "雅安市",
                "name": "芦山县",
                "id": "511826"
            },
            {
                "city": "雅安市",
                "name": "宝兴县",
                "id": "511827"
            }
        ],
        "511900": [
            {
                "city": "巴中市",
                "name": "市辖区",
                "id": "511901"
            },
            {
                "city": "巴中市",
                "name": "巴州区",
                "id": "511902"
            },
            {
                "city": "巴中市",
                "name": "恩阳区",
                "id": "511903"
            },
            {
                "city": "巴中市",
                "name": "通江县",
                "id": "511921"
            },
            {
                "city": "巴中市",
                "name": "南江县",
                "id": "511922"
            },
            {
                "city": "巴中市",
                "name": "平昌县",
                "id": "511923"
            }
        ],
        "512000": [
            {
                "city": "资阳市",
                "name": "市辖区",
                "id": "512001"
            },
            {
                "city": "资阳市",
                "name": "雁江区",
                "id": "512002"
            },
            {
                "city": "资阳市",
                "name": "安岳县",
                "id": "512021"
            },
            {
                "city": "资阳市",
                "name": "乐至县",
                "id": "512022"
            }
        ],
        "513200": [
            {
                "city": "阿坝藏族羌族自治州",
                "name": "马尔康市",
                "id": "513201"
            },
            {
                "city": "阿坝藏族羌族自治州",
                "name": "汶川县",
                "id": "513221"
            },
            {
                "city": "阿坝藏族羌族自治州",
                "name": "理县",
                "id": "513222"
            },
            {
                "city": "阿坝藏族羌族自治州",
                "name": "茂县",
                "id": "513223"
            },
            {
                "city": "阿坝藏族羌族自治州",
                "name": "松潘县",
                "id": "513224"
            },
            {
                "city": "阿坝藏族羌族自治州",
                "name": "九寨沟县",
                "id": "513225"
            },
            {
                "city": "阿坝藏族羌族自治州",
                "name": "金川县",
                "id": "513226"
            },
            {
                "city": "阿坝藏族羌族自治州",
                "name": "小金县",
                "id": "513227"
            },
            {
                "city": "阿坝藏族羌族自治州",
                "name": "黑水县",
                "id": "513228"
            },
            {
                "city": "阿坝藏族羌族自治州",
                "name": "壤塘县",
                "id": "513230"
            },
            {
                "city": "阿坝藏族羌族自治州",
                "name": "阿坝县",
                "id": "513231"
            },
            {
                "city": "阿坝藏族羌族自治州",
                "name": "若尔盖县",
                "id": "513232"
            },
            {
                "city": "阿坝藏族羌族自治州",
                "name": "红原县",
                "id": "513233"
            }
        ],
        "513300": [
            {
                "city": "甘孜藏族自治州",
                "name": "康定市",
                "id": "513301"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "泸定县",
                "id": "513322"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "丹巴县",
                "id": "513323"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "九龙县",
                "id": "513324"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "雅江县",
                "id": "513325"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "道孚县",
                "id": "513326"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "炉霍县",
                "id": "513327"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "甘孜县",
                "id": "513328"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "新龙县",
                "id": "513329"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "德格县",
                "id": "513330"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "白玉县",
                "id": "513331"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "石渠县",
                "id": "513332"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "色达县",
                "id": "513333"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "理塘县",
                "id": "513334"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "巴塘县",
                "id": "513335"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "乡城县",
                "id": "513336"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "稻城县",
                "id": "513337"
            },
            {
                "city": "甘孜藏族自治州",
                "name": "得荣县",
                "id": "513338"
            }
        ],
        "513400": [
            {
                "city": "凉山彝族自治州",
                "name": "西昌市",
                "id": "513401"
            },
            {
                "city": "凉山彝族自治州",
                "name": "木里藏族自治县",
                "id": "513422"
            },
            {
                "city": "凉山彝族自治州",
                "name": "盐源县",
                "id": "513423"
            },
            {
                "city": "凉山彝族自治州",
                "name": "德昌县",
                "id": "513424"
            },
            {
                "city": "凉山彝族自治州",
                "name": "会理县",
                "id": "513425"
            },
            {
                "city": "凉山彝族自治州",
                "name": "会东县",
                "id": "513426"
            },
            {
                "city": "凉山彝族自治州",
                "name": "宁南县",
                "id": "513427"
            },
            {
                "city": "凉山彝族自治州",
                "name": "普格县",
                "id": "513428"
            },
            {
                "city": "凉山彝族自治州",
                "name": "布拖县",
                "id": "513429"
            },
            {
                "city": "凉山彝族自治州",
                "name": "金阳县",
                "id": "513430"
            },
            {
                "city": "凉山彝族自治州",
                "name": "昭觉县",
                "id": "513431"
            },
            {
                "city": "凉山彝族自治州",
                "name": "喜德县",
                "id": "513432"
            },
            {
                "city": "凉山彝族自治州",
                "name": "冕宁县",
                "id": "513433"
            },
            {
                "city": "凉山彝族自治州",
                "name": "越西县",
                "id": "513434"
            },
            {
                "city": "凉山彝族自治州",
                "name": "甘洛县",
                "id": "513435"
            },
            {
                "city": "凉山彝族自治州",
                "name": "美姑县",
                "id": "513436"
            },
            {
                "city": "凉山彝族自治州",
                "name": "雷波县",
                "id": "513437"
            }
        ],
        "520100": [
            {
                "city": "贵阳市",
                "name": "市辖区",
                "id": "520101"
            },
            {
                "city": "贵阳市",
                "name": "南明区",
                "id": "520102"
            },
            {
                "city": "贵阳市",
                "name": "云岩区",
                "id": "520103"
            },
            {
                "city": "贵阳市",
                "name": "花溪区",
                "id": "520111"
            },
            {
                "city": "贵阳市",
                "name": "乌当区",
                "id": "520112"
            },
            {
                "city": "贵阳市",
                "name": "白云区",
                "id": "520113"
            },
            {
                "city": "贵阳市",
                "name": "观山湖区",
                "id": "520115"
            },
            {
                "city": "贵阳市",
                "name": "开阳县",
                "id": "520121"
            },
            {
                "city": "贵阳市",
                "name": "息烽县",
                "id": "520122"
            },
            {
                "city": "贵阳市",
                "name": "修文县",
                "id": "520123"
            },
            {
                "city": "贵阳市",
                "name": "清镇市",
                "id": "520181"
            }
        ],
        "520200": [
            {
                "city": "六盘水市",
                "name": "钟山区",
                "id": "520201"
            },
            {
                "city": "六盘水市",
                "name": "六枝特区",
                "id": "520203"
            },
            {
                "city": "六盘水市",
                "name": "水城县",
                "id": "520221"
            },
            {
                "city": "六盘水市",
                "name": "盘县",
                "id": "520222"
            }
        ],
        "520300": [
            {
                "city": "遵义市",
                "name": "市辖区",
                "id": "520301"
            },
            {
                "city": "遵义市",
                "name": "红花岗区",
                "id": "520302"
            },
            {
                "city": "遵义市",
                "name": "汇川区",
                "id": "520303"
            },
            {
                "city": "遵义市",
                "name": "播州区",
                "id": "520304"
            },
            {
                "city": "遵义市",
                "name": "桐梓县",
                "id": "520322"
            },
            {
                "city": "遵义市",
                "name": "绥阳县",
                "id": "520323"
            },
            {
                "city": "遵义市",
                "name": "正安县",
                "id": "520324"
            },
            {
                "city": "遵义市",
                "name": "道真仡佬族苗族自治县",
                "id": "520325"
            },
            {
                "city": "遵义市",
                "name": "务川仡佬族苗族自治县",
                "id": "520326"
            },
            {
                "city": "遵义市",
                "name": "凤冈县",
                "id": "520327"
            },
            {
                "city": "遵义市",
                "name": "湄潭县",
                "id": "520328"
            },
            {
                "city": "遵义市",
                "name": "余庆县",
                "id": "520329"
            },
            {
                "city": "遵义市",
                "name": "习水县",
                "id": "520330"
            },
            {
                "city": "遵义市",
                "name": "赤水市",
                "id": "520381"
            },
            {
                "city": "遵义市",
                "name": "仁怀市",
                "id": "520382"
            }
        ],
        "520400": [
            {
                "city": "安顺市",
                "name": "市辖区",
                "id": "520401"
            },
            {
                "city": "安顺市",
                "name": "西秀区",
                "id": "520402"
            },
            {
                "city": "安顺市",
                "name": "平坝区",
                "id": "520403"
            },
            {
                "city": "安顺市",
                "name": "普定县",
                "id": "520422"
            },
            {
                "city": "安顺市",
                "name": "镇宁布依族苗族自治县",
                "id": "520423"
            },
            {
                "city": "安顺市",
                "name": "关岭布依族苗族自治县",
                "id": "520424"
            },
            {
                "city": "安顺市",
                "name": "紫云苗族布依族自治县",
                "id": "520425"
            }
        ],
        "520500": [
            {
                "city": "毕节市",
                "name": "市辖区",
                "id": "520501"
            },
            {
                "city": "毕节市",
                "name": "七星关区",
                "id": "520502"
            },
            {
                "city": "毕节市",
                "name": "大方县",
                "id": "520521"
            },
            {
                "city": "毕节市",
                "name": "黔西县",
                "id": "520522"
            },
            {
                "city": "毕节市",
                "name": "金沙县",
                "id": "520523"
            },
            {
                "city": "毕节市",
                "name": "织金县",
                "id": "520524"
            },
            {
                "city": "毕节市",
                "name": "纳雍县",
                "id": "520525"
            },
            {
                "city": "毕节市",
                "name": "威宁彝族回族苗族自治县",
                "id": "520526"
            },
            {
                "city": "毕节市",
                "name": "赫章县",
                "id": "520527"
            }
        ],
        "520600": [
            {
                "city": "铜仁市",
                "name": "市辖区",
                "id": "520601"
            },
            {
                "city": "铜仁市",
                "name": "碧江区",
                "id": "520602"
            },
            {
                "city": "铜仁市",
                "name": "万山区",
                "id": "520603"
            },
            {
                "city": "铜仁市",
                "name": "江口县",
                "id": "520621"
            },
            {
                "city": "铜仁市",
                "name": "玉屏侗族自治县",
                "id": "520622"
            },
            {
                "city": "铜仁市",
                "name": "石阡县",
                "id": "520623"
            },
            {
                "city": "铜仁市",
                "name": "思南县",
                "id": "520624"
            },
            {
                "city": "铜仁市",
                "name": "印江土家族苗族自治县",
                "id": "520625"
            },
            {
                "city": "铜仁市",
                "name": "德江县",
                "id": "520626"
            },
            {
                "city": "铜仁市",
                "name": "沿河土家族自治县",
                "id": "520627"
            },
            {
                "city": "铜仁市",
                "name": "松桃苗族自治县",
                "id": "520628"
            }
        ],
        "522300": [
            {
                "city": "黔西南布依族苗族自治州",
                "name": "兴义市",
                "id": "522301"
            },
            {
                "city": "黔西南布依族苗族自治州",
                "name": "兴仁县",
                "id": "522322"
            },
            {
                "city": "黔西南布依族苗族自治州",
                "name": "普安县",
                "id": "522323"
            },
            {
                "city": "黔西南布依族苗族自治州",
                "name": "晴隆县",
                "id": "522324"
            },
            {
                "city": "黔西南布依族苗族自治州",
                "name": "贞丰县",
                "id": "522325"
            },
            {
                "city": "黔西南布依族苗族自治州",
                "name": "望谟县",
                "id": "522326"
            },
            {
                "city": "黔西南布依族苗族自治州",
                "name": "册亨县",
                "id": "522327"
            },
            {
                "city": "黔西南布依族苗族自治州",
                "name": "安龙县",
                "id": "522328"
            }
        ],
        "522600": [
            {
                "city": "黔东南苗族侗族自治州",
                "name": "凯里市",
                "id": "522601"
            },
            {
                "city": "黔东南苗族侗族自治州",
                "name": "黄平县",
                "id": "522622"
            },
            {
                "city": "黔东南苗族侗族自治州",
                "name": "施秉县",
                "id": "522623"
            },
            {
                "city": "黔东南苗族侗族自治州",
                "name": "三穗县",
                "id": "522624"
            },
            {
                "city": "黔东南苗族侗族自治州",
                "name": "镇远县",
                "id": "522625"
            },
            {
                "city": "黔东南苗族侗族自治州",
                "name": "岑巩县",
                "id": "522626"
            },
            {
                "city": "黔东南苗族侗族自治州",
                "name": "天柱县",
                "id": "522627"
            },
            {
                "city": "黔东南苗族侗族自治州",
                "name": "锦屏县",
                "id": "522628"
            },
            {
                "city": "黔东南苗族侗族自治州",
                "name": "剑河县",
                "id": "522629"
            },
            {
                "city": "黔东南苗族侗族自治州",
                "name": "台江县",
                "id": "522630"
            },
            {
                "city": "黔东南苗族侗族自治州",
                "name": "黎平县",
                "id": "522631"
            },
            {
                "city": "黔东南苗族侗族自治州",
                "name": "榕江县",
                "id": "522632"
            },
            {
                "city": "黔东南苗族侗族自治州",
                "name": "从江县",
                "id": "522633"
            },
            {
                "city": "黔东南苗族侗族自治州",
                "name": "雷山县",
                "id": "522634"
            },
            {
                "city": "黔东南苗族侗族自治州",
                "name": "麻江县",
                "id": "522635"
            },
            {
                "city": "黔东南苗族侗族自治州",
                "name": "丹寨县",
                "id": "522636"
            }
        ],
        "522700": [
            {
                "city": "黔南布依族苗族自治州",
                "name": "都匀市",
                "id": "522701"
            },
            {
                "city": "黔南布依族苗族自治州",
                "name": "福泉市",
                "id": "522702"
            },
            {
                "city": "黔南布依族苗族自治州",
                "name": "荔波县",
                "id": "522722"
            },
            {
                "city": "黔南布依族苗族自治州",
                "name": "贵定县",
                "id": "522723"
            },
            {
                "city": "黔南布依族苗族自治州",
                "name": "瓮安县",
                "id": "522725"
            },
            {
                "city": "黔南布依族苗族自治州",
                "name": "独山县",
                "id": "522726"
            },
            {
                "city": "黔南布依族苗族自治州",
                "name": "平塘县",
                "id": "522727"
            },
            {
                "city": "黔南布依族苗族自治州",
                "name": "罗甸县",
                "id": "522728"
            },
            {
                "city": "黔南布依族苗族自治州",
                "name": "长顺县",
                "id": "522729"
            },
            {
                "city": "黔南布依族苗族自治州",
                "name": "龙里县",
                "id": "522730"
            },
            {
                "city": "黔南布依族苗族自治州",
                "name": "惠水县",
                "id": "522731"
            },
            {
                "city": "黔南布依族苗族自治州",
                "name": "三都水族自治县",
                "id": "522732"
            }
        ],
        "530100": [
            {
                "city": "昆明市",
                "name": "市辖区",
                "id": "530101"
            },
            {
                "city": "昆明市",
                "name": "五华区",
                "id": "530102"
            },
            {
                "city": "昆明市",
                "name": "盘龙区",
                "id": "530103"
            },
            {
                "city": "昆明市",
                "name": "官渡区",
                "id": "530111"
            },
            {
                "city": "昆明市",
                "name": "西山区",
                "id": "530112"
            },
            {
                "city": "昆明市",
                "name": "东川区",
                "id": "530113"
            },
            {
                "city": "昆明市",
                "name": "呈贡区",
                "id": "530114"
            },
            {
                "city": "昆明市",
                "name": "晋宁县",
                "id": "530122"
            },
            {
                "city": "昆明市",
                "name": "富民县",
                "id": "530124"
            },
            {
                "city": "昆明市",
                "name": "宜良县",
                "id": "530125"
            },
            {
                "city": "昆明市",
                "name": "石林彝族自治县",
                "id": "530126"
            },
            {
                "city": "昆明市",
                "name": "嵩明县",
                "id": "530127"
            },
            {
                "city": "昆明市",
                "name": "禄劝彝族苗族自治县",
                "id": "530128"
            },
            {
                "city": "昆明市",
                "name": "寻甸回族彝族自治县",
                "id": "530129"
            },
            {
                "city": "昆明市",
                "name": "安宁市",
                "id": "530181"
            }
        ],
        "530300": [
            {
                "city": "曲靖市",
                "name": "市辖区",
                "id": "530301"
            },
            {
                "city": "曲靖市",
                "name": "麒麟区",
                "id": "530302"
            },
            {
                "city": "曲靖市",
                "name": "沾益区",
                "id": "530303"
            },
            {
                "city": "曲靖市",
                "name": "马龙县",
                "id": "530321"
            },
            {
                "city": "曲靖市",
                "name": "陆良县",
                "id": "530322"
            },
            {
                "city": "曲靖市",
                "name": "师宗县",
                "id": "530323"
            },
            {
                "city": "曲靖市",
                "name": "罗平县",
                "id": "530324"
            },
            {
                "city": "曲靖市",
                "name": "富源县",
                "id": "530325"
            },
            {
                "city": "曲靖市",
                "name": "会泽县",
                "id": "530326"
            },
            {
                "city": "曲靖市",
                "name": "宣威市",
                "id": "530381"
            }
        ],
        "530400": [
            {
                "city": "玉溪市",
                "name": "市辖区",
                "id": "530401"
            },
            {
                "city": "玉溪市",
                "name": "红塔区",
                "id": "530402"
            },
            {
                "city": "玉溪市",
                "name": "江川区",
                "id": "530403"
            },
            {
                "city": "玉溪市",
                "name": "澄江县",
                "id": "530422"
            },
            {
                "city": "玉溪市",
                "name": "通海县",
                "id": "530423"
            },
            {
                "city": "玉溪市",
                "name": "华宁县",
                "id": "530424"
            },
            {
                "city": "玉溪市",
                "name": "易门县",
                "id": "530425"
            },
            {
                "city": "玉溪市",
                "name": "峨山彝族自治县",
                "id": "530426"
            },
            {
                "city": "玉溪市",
                "name": "新平彝族傣族自治县",
                "id": "530427"
            },
            {
                "city": "玉溪市",
                "name": "元江哈尼族彝族傣族自治县",
                "id": "530428"
            }
        ],
        "530500": [
            {
                "city": "保山市",
                "name": "市辖区",
                "id": "530501"
            },
            {
                "city": "保山市",
                "name": "隆阳区",
                "id": "530502"
            },
            {
                "city": "保山市",
                "name": "施甸县",
                "id": "530521"
            },
            {
                "city": "保山市",
                "name": "龙陵县",
                "id": "530523"
            },
            {
                "city": "保山市",
                "name": "昌宁县",
                "id": "530524"
            },
            {
                "city": "保山市",
                "name": "腾冲市",
                "id": "530581"
            }
        ],
        "530600": [
            {
                "city": "昭通市",
                "name": "市辖区",
                "id": "530601"
            },
            {
                "city": "昭通市",
                "name": "昭阳区",
                "id": "530602"
            },
            {
                "city": "昭通市",
                "name": "鲁甸县",
                "id": "530621"
            },
            {
                "city": "昭通市",
                "name": "巧家县",
                "id": "530622"
            },
            {
                "city": "昭通市",
                "name": "盐津县",
                "id": "530623"
            },
            {
                "city": "昭通市",
                "name": "大关县",
                "id": "530624"
            },
            {
                "city": "昭通市",
                "name": "永善县",
                "id": "530625"
            },
            {
                "city": "昭通市",
                "name": "绥江县",
                "id": "530626"
            },
            {
                "city": "昭通市",
                "name": "镇雄县",
                "id": "530627"
            },
            {
                "city": "昭通市",
                "name": "彝良县",
                "id": "530628"
            },
            {
                "city": "昭通市",
                "name": "威信县",
                "id": "530629"
            },
            {
                "city": "昭通市",
                "name": "水富县",
                "id": "530630"
            }
        ],
        "530700": [
            {
                "city": "丽江市",
                "name": "市辖区",
                "id": "530701"
            },
            {
                "city": "丽江市",
                "name": "古城区",
                "id": "530702"
            },
            {
                "city": "丽江市",
                "name": "玉龙纳西族自治县",
                "id": "530721"
            },
            {
                "city": "丽江市",
                "name": "永胜县",
                "id": "530722"
            },
            {
                "city": "丽江市",
                "name": "华坪县",
                "id": "530723"
            },
            {
                "city": "丽江市",
                "name": "宁蒗彝族自治县",
                "id": "530724"
            }
        ],
        "530800": [
            {
                "city": "普洱市",
                "name": "市辖区",
                "id": "530801"
            },
            {
                "city": "普洱市",
                "name": "思茅区",
                "id": "530802"
            },
            {
                "city": "普洱市",
                "name": "宁洱哈尼族彝族自治县",
                "id": "530821"
            },
            {
                "city": "普洱市",
                "name": "墨江哈尼族自治县",
                "id": "530822"
            },
            {
                "city": "普洱市",
                "name": "景东彝族自治县",
                "id": "530823"
            },
            {
                "city": "普洱市",
                "name": "景谷傣族彝族自治县",
                "id": "530824"
            },
            {
                "city": "普洱市",
                "name": "镇沅彝族哈尼族拉祜族自治县",
                "id": "530825"
            },
            {
                "city": "普洱市",
                "name": "江城哈尼族彝族自治县",
                "id": "530826"
            },
            {
                "city": "普洱市",
                "name": "孟连傣族拉祜族佤族自治县",
                "id": "530827"
            },
            {
                "city": "普洱市",
                "name": "澜沧拉祜族自治县",
                "id": "530828"
            },
            {
                "city": "普洱市",
                "name": "西盟佤族自治县",
                "id": "530829"
            }
        ],
        "530900": [
            {
                "city": "临沧市",
                "name": "市辖区",
                "id": "530901"
            },
            {
                "city": "临沧市",
                "name": "临翔区",
                "id": "530902"
            },
            {
                "city": "临沧市",
                "name": "凤庆县",
                "id": "530921"
            },
            {
                "city": "临沧市",
                "name": "云县",
                "id": "530922"
            },
            {
                "city": "临沧市",
                "name": "永德县",
                "id": "530923"
            },
            {
                "city": "临沧市",
                "name": "镇康县",
                "id": "530924"
            },
            {
                "city": "临沧市",
                "name": "双江拉祜族佤族布朗族傣族自治县",
                "id": "530925"
            },
            {
                "city": "临沧市",
                "name": "耿马傣族佤族自治县",
                "id": "530926"
            },
            {
                "city": "临沧市",
                "name": "沧源佤族自治县",
                "id": "530927"
            }
        ],
        "532300": [
            {
                "city": "楚雄彝族自治州",
                "name": "楚雄市",
                "id": "532301"
            },
            {
                "city": "楚雄彝族自治州",
                "name": "双柏县",
                "id": "532322"
            },
            {
                "city": "楚雄彝族自治州",
                "name": "牟定县",
                "id": "532323"
            },
            {
                "city": "楚雄彝族自治州",
                "name": "南华县",
                "id": "532324"
            },
            {
                "city": "楚雄彝族自治州",
                "name": "姚安县",
                "id": "532325"
            },
            {
                "city": "楚雄彝族自治州",
                "name": "大姚县",
                "id": "532326"
            },
            {
                "city": "楚雄彝族自治州",
                "name": "永仁县",
                "id": "532327"
            },
            {
                "city": "楚雄彝族自治州",
                "name": "元谋县",
                "id": "532328"
            },
            {
                "city": "楚雄彝族自治州",
                "name": "武定县",
                "id": "532329"
            },
            {
                "city": "楚雄彝族自治州",
                "name": "禄丰县",
                "id": "532331"
            }
        ],
        "532500": [
            {
                "city": "红河哈尼族彝族自治州",
                "name": "个旧市",
                "id": "532501"
            },
            {
                "city": "红河哈尼族彝族自治州",
                "name": "开远市",
                "id": "532502"
            },
            {
                "city": "红河哈尼族彝族自治州",
                "name": "蒙自市",
                "id": "532503"
            },
            {
                "city": "红河哈尼族彝族自治州",
                "name": "弥勒市",
                "id": "532504"
            },
            {
                "city": "红河哈尼族彝族自治州",
                "name": "屏边苗族自治县",
                "id": "532523"
            },
            {
                "city": "红河哈尼族彝族自治州",
                "name": "建水县",
                "id": "532524"
            },
            {
                "city": "红河哈尼族彝族自治州",
                "name": "石屏县",
                "id": "532525"
            },
            {
                "city": "红河哈尼族彝族自治州",
                "name": "泸西县",
                "id": "532527"
            },
            {
                "city": "红河哈尼族彝族自治州",
                "name": "元阳县",
                "id": "532528"
            },
            {
                "city": "红河哈尼族彝族自治州",
                "name": "红河县",
                "id": "532529"
            },
            {
                "city": "红河哈尼族彝族自治州",
                "name": "金平苗族瑶族傣族自治县",
                "id": "532530"
            },
            {
                "city": "红河哈尼族彝族自治州",
                "name": "绿春县",
                "id": "532531"
            },
            {
                "city": "红河哈尼族彝族自治州",
                "name": "河口瑶族自治县",
                "id": "532532"
            }
        ],
        "532600": [
            {
                "city": "文山壮族苗族自治州",
                "name": "文山市",
                "id": "532601"
            },
            {
                "city": "文山壮族苗族自治州",
                "name": "砚山县",
                "id": "532622"
            },
            {
                "city": "文山壮族苗族自治州",
                "name": "西畴县",
                "id": "532623"
            },
            {
                "city": "文山壮族苗族自治州",
                "name": "麻栗坡县",
                "id": "532624"
            },
            {
                "city": "文山壮族苗族自治州",
                "name": "马关县",
                "id": "532625"
            },
            {
                "city": "文山壮族苗族自治州",
                "name": "丘北县",
                "id": "532626"
            },
            {
                "city": "文山壮族苗族自治州",
                "name": "广南县",
                "id": "532627"
            },
            {
                "city": "文山壮族苗族自治州",
                "name": "富宁县",
                "id": "532628"
            }
        ],
        "532800": [
            {
                "city": "西双版纳傣族自治州",
                "name": "景洪市",
                "id": "532801"
            },
            {
                "city": "西双版纳傣族自治州",
                "name": "勐海县",
                "id": "532822"
            },
            {
                "city": "西双版纳傣族自治州",
                "name": "勐腊县",
                "id": "532823"
            }
        ],
        "532900": [
            {
                "city": "大理白族自治州",
                "name": "大理市",
                "id": "532901"
            },
            {
                "city": "大理白族自治州",
                "name": "漾濞彝族自治县",
                "id": "532922"
            },
            {
                "city": "大理白族自治州",
                "name": "祥云县",
                "id": "532923"
            },
            {
                "city": "大理白族自治州",
                "name": "宾川县",
                "id": "532924"
            },
            {
                "city": "大理白族自治州",
                "name": "弥渡县",
                "id": "532925"
            },
            {
                "city": "大理白族自治州",
                "name": "南涧彝族自治县",
                "id": "532926"
            },
            {
                "city": "大理白族自治州",
                "name": "巍山彝族回族自治县",
                "id": "532927"
            },
            {
                "city": "大理白族自治州",
                "name": "永平县",
                "id": "532928"
            },
            {
                "city": "大理白族自治州",
                "name": "云龙县",
                "id": "532929"
            },
            {
                "city": "大理白族自治州",
                "name": "洱源县",
                "id": "532930"
            },
            {
                "city": "大理白族自治州",
                "name": "剑川县",
                "id": "532931"
            },
            {
                "city": "大理白族自治州",
                "name": "鹤庆县",
                "id": "532932"
            }
        ],
        "533100": [
            {
                "city": "德宏傣族景颇族自治州",
                "name": "瑞丽市",
                "id": "533102"
            },
            {
                "city": "德宏傣族景颇族自治州",
                "name": "芒市",
                "id": "533103"
            },
            {
                "city": "德宏傣族景颇族自治州",
                "name": "梁河县",
                "id": "533122"
            },
            {
                "city": "德宏傣族景颇族自治州",
                "name": "盈江县",
                "id": "533123"
            },
            {
                "city": "德宏傣族景颇族自治州",
                "name": "陇川县",
                "id": "533124"
            }
        ],
        "533300": [
            {
                "city": "怒江傈僳族自治州",
                "name": "泸水市",
                "id": "533301"
            },
            {
                "city": "怒江傈僳族自治州",
                "name": "福贡县",
                "id": "533323"
            },
            {
                "city": "怒江傈僳族自治州",
                "name": "贡山独龙族怒族自治县",
                "id": "533324"
            },
            {
                "city": "怒江傈僳族自治州",
                "name": "兰坪白族普米族自治县",
                "id": "533325"
            }
        ],
        "533400": [
            {
                "city": "迪庆藏族自治州",
                "name": "香格里拉市",
                "id": "533401"
            },
            {
                "city": "迪庆藏族自治州",
                "name": "德钦县",
                "id": "533422"
            },
            {
                "city": "迪庆藏族自治州",
                "name": "维西傈僳族自治县",
                "id": "533423"
            }
        ],
        "540100": [
            {
                "city": "拉萨市",
                "name": "市辖区",
                "id": "540101"
            },
            {
                "city": "拉萨市",
                "name": "城关区",
                "id": "540102"
            },
            {
                "city": "拉萨市",
                "name": "堆龙德庆区",
                "id": "540103"
            },
            {
                "city": "拉萨市",
                "name": "林周县",
                "id": "540121"
            },
            {
                "city": "拉萨市",
                "name": "当雄县",
                "id": "540122"
            },
            {
                "city": "拉萨市",
                "name": "尼木县",
                "id": "540123"
            },
            {
                "city": "拉萨市",
                "name": "曲水县",
                "id": "540124"
            },
            {
                "city": "拉萨市",
                "name": "达孜县",
                "id": "540126"
            },
            {
                "city": "拉萨市",
                "name": "墨竹工卡县",
                "id": "540127"
            }
        ],
        "540200": [
            {
                "city": "日喀则市",
                "name": "桑珠孜区",
                "id": "540202"
            },
            {
                "city": "日喀则市",
                "name": "南木林县",
                "id": "540221"
            },
            {
                "city": "日喀则市",
                "name": "江孜县",
                "id": "540222"
            },
            {
                "city": "日喀则市",
                "name": "定日县",
                "id": "540223"
            },
            {
                "city": "日喀则市",
                "name": "萨迦县",
                "id": "540224"
            },
            {
                "city": "日喀则市",
                "name": "拉孜县",
                "id": "540225"
            },
            {
                "city": "日喀则市",
                "name": "昂仁县",
                "id": "540226"
            },
            {
                "city": "日喀则市",
                "name": "谢通门县",
                "id": "540227"
            },
            {
                "city": "日喀则市",
                "name": "白朗县",
                "id": "540228"
            },
            {
                "city": "日喀则市",
                "name": "仁布县",
                "id": "540229"
            },
            {
                "city": "日喀则市",
                "name": "康马县",
                "id": "540230"
            },
            {
                "city": "日喀则市",
                "name": "定结县",
                "id": "540231"
            },
            {
                "city": "日喀则市",
                "name": "仲巴县",
                "id": "540232"
            },
            {
                "city": "日喀则市",
                "name": "亚东县",
                "id": "540233"
            },
            {
                "city": "日喀则市",
                "name": "吉隆县",
                "id": "540234"
            },
            {
                "city": "日喀则市",
                "name": "聂拉木县",
                "id": "540235"
            },
            {
                "city": "日喀则市",
                "name": "萨嘎县",
                "id": "540236"
            },
            {
                "city": "日喀则市",
                "name": "岗巴县",
                "id": "540237"
            }
        ],
        "540300": [
            {
                "city": "昌都市",
                "name": "卡若区",
                "id": "540302"
            },
            {
                "city": "昌都市",
                "name": "江达县",
                "id": "540321"
            },
            {
                "city": "昌都市",
                "name": "贡觉县",
                "id": "540322"
            },
            {
                "city": "昌都市",
                "name": "类乌齐县",
                "id": "540323"
            },
            {
                "city": "昌都市",
                "name": "丁青县",
                "id": "540324"
            },
            {
                "city": "昌都市",
                "name": "察雅县",
                "id": "540325"
            },
            {
                "city": "昌都市",
                "name": "八宿县",
                "id": "540326"
            },
            {
                "city": "昌都市",
                "name": "左贡县",
                "id": "540327"
            },
            {
                "city": "昌都市",
                "name": "芒康县",
                "id": "540328"
            },
            {
                "city": "昌都市",
                "name": "洛隆县",
                "id": "540329"
            },
            {
                "city": "昌都市",
                "name": "边坝县",
                "id": "540330"
            }
        ],
        "540400": [
            {
                "city": "林芝市",
                "name": "巴宜区",
                "id": "540402"
            },
            {
                "city": "林芝市",
                "name": "工布江达县",
                "id": "540421"
            },
            {
                "city": "林芝市",
                "name": "米林县",
                "id": "540422"
            },
            {
                "city": "林芝市",
                "name": "墨脱县",
                "id": "540423"
            },
            {
                "city": "林芝市",
                "name": "波密县",
                "id": "540424"
            },
            {
                "city": "林芝市",
                "name": "察隅县",
                "id": "540425"
            },
            {
                "city": "林芝市",
                "name": "朗县",
                "id": "540426"
            }
        ],
        "540500": [
            {
                "city": "山南市",
                "name": "市辖区",
                "id": "540501"
            },
            {
                "city": "山南市",
                "name": "乃东区",
                "id": "540502"
            },
            {
                "city": "山南市",
                "name": "扎囊县",
                "id": "540521"
            },
            {
                "city": "山南市",
                "name": "贡嘎县",
                "id": "540522"
            },
            {
                "city": "山南市",
                "name": "桑日县",
                "id": "540523"
            },
            {
                "city": "山南市",
                "name": "琼结县",
                "id": "540524"
            },
            {
                "city": "山南市",
                "name": "曲松县",
                "id": "540525"
            },
            {
                "city": "山南市",
                "name": "措美县",
                "id": "540526"
            },
            {
                "city": "山南市",
                "name": "洛扎县",
                "id": "540527"
            },
            {
                "city": "山南市",
                "name": "加查县",
                "id": "540528"
            },
            {
                "city": "山南市",
                "name": "隆子县",
                "id": "540529"
            },
            {
                "city": "山南市",
                "name": "错那县",
                "id": "540530"
            },
            {
                "city": "山南市",
                "name": "浪卡子县",
                "id": "540531"
            }
        ],
        "542400": [
            {
                "city": "那曲地区",
                "name": "那曲县",
                "id": "542421"
            },
            {
                "city": "那曲地区",
                "name": "嘉黎县",
                "id": "542422"
            },
            {
                "city": "那曲地区",
                "name": "比如县",
                "id": "542423"
            },
            {
                "city": "那曲地区",
                "name": "聂荣县",
                "id": "542424"
            },
            {
                "city": "那曲地区",
                "name": "安多县",
                "id": "542425"
            },
            {
                "city": "那曲地区",
                "name": "申扎县",
                "id": "542426"
            },
            {
                "city": "那曲地区",
                "name": "索县",
                "id": "542427"
            },
            {
                "city": "那曲地区",
                "name": "班戈县",
                "id": "542428"
            },
            {
                "city": "那曲地区",
                "name": "巴青县",
                "id": "542429"
            },
            {
                "city": "那曲地区",
                "name": "尼玛县",
                "id": "542430"
            },
            {
                "city": "那曲地区",
                "name": "双湖县",
                "id": "542431"
            }
        ],
        "542500": [
            {
                "city": "阿里地区",
                "name": "普兰县",
                "id": "542521"
            },
            {
                "city": "阿里地区",
                "name": "札达县",
                "id": "542522"
            },
            {
                "city": "阿里地区",
                "name": "噶尔县",
                "id": "542523"
            },
            {
                "city": "阿里地区",
                "name": "日土县",
                "id": "542524"
            },
            {
                "city": "阿里地区",
                "name": "革吉县",
                "id": "542525"
            },
            {
                "city": "阿里地区",
                "name": "改则县",
                "id": "542526"
            },
            {
                "city": "阿里地区",
                "name": "措勤县",
                "id": "542527"
            }
        ],
        "610100": [
            {
                "city": "西安市",
                "name": "市辖区",
                "id": "610101"
            },
            {
                "city": "西安市",
                "name": "新城区",
                "id": "610102"
            },
            {
                "city": "西安市",
                "name": "碑林区",
                "id": "610103"
            },
            {
                "city": "西安市",
                "name": "莲湖区",
                "id": "610104"
            },
            {
                "city": "西安市",
                "name": "灞桥区",
                "id": "610111"
            },
            {
                "city": "西安市",
                "name": "未央区",
                "id": "610112"
            },
            {
                "city": "西安市",
                "name": "雁塔区",
                "id": "610113"
            },
            {
                "city": "西安市",
                "name": "阎良区",
                "id": "610114"
            },
            {
                "city": "西安市",
                "name": "临潼区",
                "id": "610115"
            },
            {
                "city": "西安市",
                "name": "长安区",
                "id": "610116"
            },
            {
                "city": "西安市",
                "name": "高陵区",
                "id": "610117"
            },
            {
                "city": "西安市",
                "name": "蓝田县",
                "id": "610122"
            },
            {
                "city": "西安市",
                "name": "周至县",
                "id": "610124"
            },
            {
                "city": "西安市",
                "name": "户县",
                "id": "610125"
            }
        ],
        "610200": [
            {
                "city": "铜川市",
                "name": "市辖区",
                "id": "610201"
            },
            {
                "city": "铜川市",
                "name": "王益区",
                "id": "610202"
            },
            {
                "city": "铜川市",
                "name": "印台区",
                "id": "610203"
            },
            {
                "city": "铜川市",
                "name": "耀州区",
                "id": "610204"
            },
            {
                "city": "铜川市",
                "name": "宜君县",
                "id": "610222"
            }
        ],
        "610300": [
            {
                "city": "宝鸡市",
                "name": "市辖区",
                "id": "610301"
            },
            {
                "city": "宝鸡市",
                "name": "渭滨区",
                "id": "610302"
            },
            {
                "city": "宝鸡市",
                "name": "金台区",
                "id": "610303"
            },
            {
                "city": "宝鸡市",
                "name": "陈仓区",
                "id": "610304"
            },
            {
                "city": "宝鸡市",
                "name": "凤翔县",
                "id": "610322"
            },
            {
                "city": "宝鸡市",
                "name": "岐山县",
                "id": "610323"
            },
            {
                "city": "宝鸡市",
                "name": "扶风县",
                "id": "610324"
            },
            {
                "city": "宝鸡市",
                "name": "眉县",
                "id": "610326"
            },
            {
                "city": "宝鸡市",
                "name": "陇县",
                "id": "610327"
            },
            {
                "city": "宝鸡市",
                "name": "千阳县",
                "id": "610328"
            },
            {
                "city": "宝鸡市",
                "name": "麟游县",
                "id": "610329"
            },
            {
                "city": "宝鸡市",
                "name": "凤县",
                "id": "610330"
            },
            {
                "city": "宝鸡市",
                "name": "太白县",
                "id": "610331"
            }
        ],
        "610400": [
            {
                "city": "咸阳市",
                "name": "市辖区",
                "id": "610401"
            },
            {
                "city": "咸阳市",
                "name": "秦都区",
                "id": "610402"
            },
            {
                "city": "咸阳市",
                "name": "杨陵区",
                "id": "610403"
            },
            {
                "city": "咸阳市",
                "name": "渭城区",
                "id": "610404"
            },
            {
                "city": "咸阳市",
                "name": "三原县",
                "id": "610422"
            },
            {
                "city": "咸阳市",
                "name": "泾阳县",
                "id": "610423"
            },
            {
                "city": "咸阳市",
                "name": "乾县",
                "id": "610424"
            },
            {
                "city": "咸阳市",
                "name": "礼泉县",
                "id": "610425"
            },
            {
                "city": "咸阳市",
                "name": "永寿县",
                "id": "610426"
            },
            {
                "city": "咸阳市",
                "name": "彬县",
                "id": "610427"
            },
            {
                "city": "咸阳市",
                "name": "长武县",
                "id": "610428"
            },
            {
                "city": "咸阳市",
                "name": "旬邑县",
                "id": "610429"
            },
            {
                "city": "咸阳市",
                "name": "淳化县",
                "id": "610430"
            },
            {
                "city": "咸阳市",
                "name": "武功县",
                "id": "610431"
            },
            {
                "city": "咸阳市",
                "name": "兴平市",
                "id": "610481"
            }
        ],
        "610500": [
            {
                "city": "渭南市",
                "name": "市辖区",
                "id": "610501"
            },
            {
                "city": "渭南市",
                "name": "临渭区",
                "id": "610502"
            },
            {
                "city": "渭南市",
                "name": "华州区",
                "id": "610503"
            },
            {
                "city": "渭南市",
                "name": "潼关县",
                "id": "610522"
            },
            {
                "city": "渭南市",
                "name": "大荔县",
                "id": "610523"
            },
            {
                "city": "渭南市",
                "name": "合阳县",
                "id": "610524"
            },
            {
                "city": "渭南市",
                "name": "澄城县",
                "id": "610525"
            },
            {
                "city": "渭南市",
                "name": "蒲城县",
                "id": "610526"
            },
            {
                "city": "渭南市",
                "name": "白水县",
                "id": "610527"
            },
            {
                "city": "渭南市",
                "name": "富平县",
                "id": "610528"
            },
            {
                "city": "渭南市",
                "name": "韩城市",
                "id": "610581"
            },
            {
                "city": "渭南市",
                "name": "华阴市",
                "id": "610582"
            }
        ],
        "610600": [
            {
                "city": "延安市",
                "name": "市辖区",
                "id": "610601"
            },
            {
                "city": "延安市",
                "name": "宝塔区",
                "id": "610602"
            },
            {
                "city": "延安市",
                "name": "安塞区",
                "id": "610603"
            },
            {
                "city": "延安市",
                "name": "延长县",
                "id": "610621"
            },
            {
                "city": "延安市",
                "name": "延川县",
                "id": "610622"
            },
            {
                "city": "延安市",
                "name": "子长县",
                "id": "610623"
            },
            {
                "city": "延安市",
                "name": "志丹县",
                "id": "610625"
            },
            {
                "city": "延安市",
                "name": "吴起县",
                "id": "610626"
            },
            {
                "city": "延安市",
                "name": "甘泉县",
                "id": "610627"
            },
            {
                "city": "延安市",
                "name": "富县",
                "id": "610628"
            },
            {
                "city": "延安市",
                "name": "洛川县",
                "id": "610629"
            },
            {
                "city": "延安市",
                "name": "宜川县",
                "id": "610630"
            },
            {
                "city": "延安市",
                "name": "黄龙县",
                "id": "610631"
            },
            {
                "city": "延安市",
                "name": "黄陵县",
                "id": "610632"
            }
        ],
        "610700": [
            {
                "city": "汉中市",
                "name": "市辖区",
                "id": "610701"
            },
            {
                "city": "汉中市",
                "name": "汉台区",
                "id": "610702"
            },
            {
                "city": "汉中市",
                "name": "南郑县",
                "id": "610721"
            },
            {
                "city": "汉中市",
                "name": "城固县",
                "id": "610722"
            },
            {
                "city": "汉中市",
                "name": "洋县",
                "id": "610723"
            },
            {
                "city": "汉中市",
                "name": "西乡县",
                "id": "610724"
            },
            {
                "city": "汉中市",
                "name": "勉县",
                "id": "610725"
            },
            {
                "city": "汉中市",
                "name": "宁强县",
                "id": "610726"
            },
            {
                "city": "汉中市",
                "name": "略阳县",
                "id": "610727"
            },
            {
                "city": "汉中市",
                "name": "镇巴县",
                "id": "610728"
            },
            {
                "city": "汉中市",
                "name": "留坝县",
                "id": "610729"
            },
            {
                "city": "汉中市",
                "name": "佛坪县",
                "id": "610730"
            }
        ],
        "610800": [
            {
                "city": "榆林市",
                "name": "市辖区",
                "id": "610801"
            },
            {
                "city": "榆林市",
                "name": "榆阳区",
                "id": "610802"
            },
            {
                "city": "榆林市",
                "name": "横山区",
                "id": "610803"
            },
            {
                "city": "榆林市",
                "name": "神木县",
                "id": "610821"
            },
            {
                "city": "榆林市",
                "name": "府谷县",
                "id": "610822"
            },
            {
                "city": "榆林市",
                "name": "靖边县",
                "id": "610824"
            },
            {
                "city": "榆林市",
                "name": "定边县",
                "id": "610825"
            },
            {
                "city": "榆林市",
                "name": "绥德县",
                "id": "610826"
            },
            {
                "city": "榆林市",
                "name": "米脂县",
                "id": "610827"
            },
            {
                "city": "榆林市",
                "name": "佳县",
                "id": "610828"
            },
            {
                "city": "榆林市",
                "name": "吴堡县",
                "id": "610829"
            },
            {
                "city": "榆林市",
                "name": "清涧县",
                "id": "610830"
            },
            {
                "city": "榆林市",
                "name": "子洲县",
                "id": "610831"
            }
        ],
        "610900": [
            {
                "city": "安康市",
                "name": "市辖区",
                "id": "610901"
            },
            {
                "city": "安康市",
                "name": "汉滨区",
                "id": "610902"
            },
            {
                "city": "安康市",
                "name": "汉阴县",
                "id": "610921"
            },
            {
                "city": "安康市",
                "name": "石泉县",
                "id": "610922"
            },
            {
                "city": "安康市",
                "name": "宁陕县",
                "id": "610923"
            },
            {
                "city": "安康市",
                "name": "紫阳县",
                "id": "610924"
            },
            {
                "city": "安康市",
                "name": "岚皋县",
                "id": "610925"
            },
            {
                "city": "安康市",
                "name": "平利县",
                "id": "610926"
            },
            {
                "city": "安康市",
                "name": "镇坪县",
                "id": "610927"
            },
            {
                "city": "安康市",
                "name": "旬阳县",
                "id": "610928"
            },
            {
                "city": "安康市",
                "name": "白河县",
                "id": "610929"
            }
        ],
        "611000": [
            {
                "city": "商洛市",
                "name": "市辖区",
                "id": "611001"
            },
            {
                "city": "商洛市",
                "name": "商州区",
                "id": "611002"
            },
            {
                "city": "商洛市",
                "name": "洛南县",
                "id": "611021"
            },
            {
                "city": "商洛市",
                "name": "丹凤县",
                "id": "611022"
            },
            {
                "city": "商洛市",
                "name": "商南县",
                "id": "611023"
            },
            {
                "city": "商洛市",
                "name": "山阳县",
                "id": "611024"
            },
            {
                "city": "商洛市",
                "name": "镇安县",
                "id": "611025"
            },
            {
                "city": "商洛市",
                "name": "柞水县",
                "id": "611026"
            }
        ],
        "620100": [
            {
                "city": "兰州市",
                "name": "市辖区",
                "id": "620101"
            },
            {
                "city": "兰州市",
                "name": "城关区",
                "id": "620102"
            },
            {
                "city": "兰州市",
                "name": "七里河区",
                "id": "620103"
            },
            {
                "city": "兰州市",
                "name": "西固区",
                "id": "620104"
            },
            {
                "city": "兰州市",
                "name": "安宁区",
                "id": "620105"
            },
            {
                "city": "兰州市",
                "name": "红古区",
                "id": "620111"
            },
            {
                "city": "兰州市",
                "name": "永登县",
                "id": "620121"
            },
            {
                "city": "兰州市",
                "name": "皋兰县",
                "id": "620122"
            },
            {
                "city": "兰州市",
                "name": "榆中县",
                "id": "620123"
            }
        ],
        "620200": [
            {
                "city": "嘉峪关市",
                "name": "市辖区",
                "id": "620201"
            }
        ],
        "620300": [
            {
                "city": "金昌市",
                "name": "市辖区",
                "id": "620301"
            },
            {
                "city": "金昌市",
                "name": "金川区",
                "id": "620302"
            },
            {
                "city": "金昌市",
                "name": "永昌县",
                "id": "620321"
            }
        ],
        "620400": [
            {
                "city": "白银市",
                "name": "市辖区",
                "id": "620401"
            },
            {
                "city": "白银市",
                "name": "白银区",
                "id": "620402"
            },
            {
                "city": "白银市",
                "name": "平川区",
                "id": "620403"
            },
            {
                "city": "白银市",
                "name": "靖远县",
                "id": "620421"
            },
            {
                "city": "白银市",
                "name": "会宁县",
                "id": "620422"
            },
            {
                "city": "白银市",
                "name": "景泰县",
                "id": "620423"
            }
        ],
        "620500": [
            {
                "city": "天水市",
                "name": "市辖区",
                "id": "620501"
            },
            {
                "city": "天水市",
                "name": "秦州区",
                "id": "620502"
            },
            {
                "city": "天水市",
                "name": "麦积区",
                "id": "620503"
            },
            {
                "city": "天水市",
                "name": "清水县",
                "id": "620521"
            },
            {
                "city": "天水市",
                "name": "秦安县",
                "id": "620522"
            },
            {
                "city": "天水市",
                "name": "甘谷县",
                "id": "620523"
            },
            {
                "city": "天水市",
                "name": "武山县",
                "id": "620524"
            },
            {
                "city": "天水市",
                "name": "张家川回族自治县",
                "id": "620525"
            }
        ],
        "620600": [
            {
                "city": "武威市",
                "name": "市辖区",
                "id": "620601"
            },
            {
                "city": "武威市",
                "name": "凉州区",
                "id": "620602"
            },
            {
                "city": "武威市",
                "name": "民勤县",
                "id": "620621"
            },
            {
                "city": "武威市",
                "name": "古浪县",
                "id": "620622"
            },
            {
                "city": "武威市",
                "name": "天祝藏族自治县",
                "id": "620623"
            }
        ],
        "620700": [
            {
                "city": "张掖市",
                "name": "市辖区",
                "id": "620701"
            },
            {
                "city": "张掖市",
                "name": "甘州区",
                "id": "620702"
            },
            {
                "city": "张掖市",
                "name": "肃南裕固族自治县",
                "id": "620721"
            },
            {
                "city": "张掖市",
                "name": "民乐县",
                "id": "620722"
            },
            {
                "city": "张掖市",
                "name": "临泽县",
                "id": "620723"
            },
            {
                "city": "张掖市",
                "name": "高台县",
                "id": "620724"
            },
            {
                "city": "张掖市",
                "name": "山丹县",
                "id": "620725"
            }
        ],
        "620800": [
            {
                "city": "平凉市",
                "name": "市辖区",
                "id": "620801"
            },
            {
                "city": "平凉市",
                "name": "崆峒区",
                "id": "620802"
            },
            {
                "city": "平凉市",
                "name": "泾川县",
                "id": "620821"
            },
            {
                "city": "平凉市",
                "name": "灵台县",
                "id": "620822"
            },
            {
                "city": "平凉市",
                "name": "崇信县",
                "id": "620823"
            },
            {
                "city": "平凉市",
                "name": "华亭县",
                "id": "620824"
            },
            {
                "city": "平凉市",
                "name": "庄浪县",
                "id": "620825"
            },
            {
                "city": "平凉市",
                "name": "静宁县",
                "id": "620826"
            }
        ],
        "620900": [
            {
                "city": "酒泉市",
                "name": "市辖区",
                "id": "620901"
            },
            {
                "city": "酒泉市",
                "name": "肃州区",
                "id": "620902"
            },
            {
                "city": "酒泉市",
                "name": "金塔县",
                "id": "620921"
            },
            {
                "city": "酒泉市",
                "name": "瓜州县",
                "id": "620922"
            },
            {
                "city": "酒泉市",
                "name": "肃北蒙古族自治县",
                "id": "620923"
            },
            {
                "city": "酒泉市",
                "name": "阿克塞哈萨克族自治县",
                "id": "620924"
            },
            {
                "city": "酒泉市",
                "name": "玉门市",
                "id": "620981"
            },
            {
                "city": "酒泉市",
                "name": "敦煌市",
                "id": "620982"
            }
        ],
        "621000": [
            {
                "city": "庆阳市",
                "name": "市辖区",
                "id": "621001"
            },
            {
                "city": "庆阳市",
                "name": "西峰区",
                "id": "621002"
            },
            {
                "city": "庆阳市",
                "name": "庆城县",
                "id": "621021"
            },
            {
                "city": "庆阳市",
                "name": "环县",
                "id": "621022"
            },
            {
                "city": "庆阳市",
                "name": "华池县",
                "id": "621023"
            },
            {
                "city": "庆阳市",
                "name": "合水县",
                "id": "621024"
            },
            {
                "city": "庆阳市",
                "name": "正宁县",
                "id": "621025"
            },
            {
                "city": "庆阳市",
                "name": "宁县",
                "id": "621026"
            },
            {
                "city": "庆阳市",
                "name": "镇原县",
                "id": "621027"
            }
        ],
        "621100": [
            {
                "city": "定西市",
                "name": "市辖区",
                "id": "621101"
            },
            {
                "city": "定西市",
                "name": "安定区",
                "id": "621102"
            },
            {
                "city": "定西市",
                "name": "通渭县",
                "id": "621121"
            },
            {
                "city": "定西市",
                "name": "陇西县",
                "id": "621122"
            },
            {
                "city": "定西市",
                "name": "渭源县",
                "id": "621123"
            },
            {
                "city": "定西市",
                "name": "临洮县",
                "id": "621124"
            },
            {
                "city": "定西市",
                "name": "漳县",
                "id": "621125"
            },
            {
                "city": "定西市",
                "name": "岷县",
                "id": "621126"
            }
        ],
        "621200": [
            {
                "city": "陇南市",
                "name": "市辖区",
                "id": "621201"
            },
            {
                "city": "陇南市",
                "name": "武都区",
                "id": "621202"
            },
            {
                "city": "陇南市",
                "name": "成县",
                "id": "621221"
            },
            {
                "city": "陇南市",
                "name": "文县",
                "id": "621222"
            },
            {
                "city": "陇南市",
                "name": "宕昌县",
                "id": "621223"
            },
            {
                "city": "陇南市",
                "name": "康县",
                "id": "621224"
            },
            {
                "city": "陇南市",
                "name": "西和县",
                "id": "621225"
            },
            {
                "city": "陇南市",
                "name": "礼县",
                "id": "621226"
            },
            {
                "city": "陇南市",
                "name": "徽县",
                "id": "621227"
            },
            {
                "city": "陇南市",
                "name": "两当县",
                "id": "621228"
            }
        ],
        "622900": [
            {
                "city": "临夏回族自治州",
                "name": "临夏市",
                "id": "622901"
            },
            {
                "city": "临夏回族自治州",
                "name": "临夏县",
                "id": "622921"
            },
            {
                "city": "临夏回族自治州",
                "name": "康乐县",
                "id": "622922"
            },
            {
                "city": "临夏回族自治州",
                "name": "永靖县",
                "id": "622923"
            },
            {
                "city": "临夏回族自治州",
                "name": "广河县",
                "id": "622924"
            },
            {
                "city": "临夏回族自治州",
                "name": "和政县",
                "id": "622925"
            },
            {
                "city": "临夏回族自治州",
                "name": "东乡族自治县",
                "id": "622926"
            },
            {
                "city": "临夏回族自治州",
                "name": "积石山保安族东乡族撒拉族自治县",
                "id": "622927"
            }
        ],
        "623000": [
            {
                "city": "甘南藏族自治州",
                "name": "合作市",
                "id": "623001"
            },
            {
                "city": "甘南藏族自治州",
                "name": "临潭县",
                "id": "623021"
            },
            {
                "city": "甘南藏族自治州",
                "name": "卓尼县",
                "id": "623022"
            },
            {
                "city": "甘南藏族自治州",
                "name": "舟曲县",
                "id": "623023"
            },
            {
                "city": "甘南藏族自治州",
                "name": "迭部县",
                "id": "623024"
            },
            {
                "city": "甘南藏族自治州",
                "name": "玛曲县",
                "id": "623025"
            },
            {
                "city": "甘南藏族自治州",
                "name": "碌曲县",
                "id": "623026"
            },
            {
                "city": "甘南藏族自治州",
                "name": "夏河县",
                "id": "623027"
            }
        ],
        "630100": [
            {
                "city": "西宁市",
                "name": "市辖区",
                "id": "630101"
            },
            {
                "city": "西宁市",
                "name": "城东区",
                "id": "630102"
            },
            {
                "city": "西宁市",
                "name": "城中区",
                "id": "630103"
            },
            {
                "city": "西宁市",
                "name": "城西区",
                "id": "630104"
            },
            {
                "city": "西宁市",
                "name": "城北区",
                "id": "630105"
            },
            {
                "city": "西宁市",
                "name": "大通回族土族自治县",
                "id": "630121"
            },
            {
                "city": "西宁市",
                "name": "湟中县",
                "id": "630122"
            },
            {
                "city": "西宁市",
                "name": "湟源县",
                "id": "630123"
            }
        ],
        "630200": [
            {
                "city": "海东市",
                "name": "乐都区",
                "id": "630202"
            },
            {
                "city": "海东市",
                "name": "平安区",
                "id": "630203"
            },
            {
                "city": "海东市",
                "name": "民和回族土族自治县",
                "id": "630222"
            },
            {
                "city": "海东市",
                "name": "互助土族自治县",
                "id": "630223"
            },
            {
                "city": "海东市",
                "name": "化隆回族自治县",
                "id": "630224"
            },
            {
                "city": "海东市",
                "name": "循化撒拉族自治县",
                "id": "630225"
            }
        ],
        "632200": [
            {
                "city": "海北藏族自治州",
                "name": "门源回族自治县",
                "id": "632221"
            },
            {
                "city": "海北藏族自治州",
                "name": "祁连县",
                "id": "632222"
            },
            {
                "city": "海北藏族自治州",
                "name": "海晏县",
                "id": "632223"
            },
            {
                "city": "海北藏族自治州",
                "name": "刚察县",
                "id": "632224"
            }
        ],
        "632300": [
            {
                "city": "黄南藏族自治州",
                "name": "同仁县",
                "id": "632321"
            },
            {
                "city": "黄南藏族自治州",
                "name": "尖扎县",
                "id": "632322"
            },
            {
                "city": "黄南藏族自治州",
                "name": "泽库县",
                "id": "632323"
            },
            {
                "city": "黄南藏族自治州",
                "name": "河南蒙古族自治县",
                "id": "632324"
            }
        ],
        "632500": [
            {
                "city": "海南藏族自治州",
                "name": "共和县",
                "id": "632521"
            },
            {
                "city": "海南藏族自治州",
                "name": "同德县",
                "id": "632522"
            },
            {
                "city": "海南藏族自治州",
                "name": "贵德县",
                "id": "632523"
            },
            {
                "city": "海南藏族自治州",
                "name": "兴海县",
                "id": "632524"
            },
            {
                "city": "海南藏族自治州",
                "name": "贵南县",
                "id": "632525"
            }
        ],
        "632600": [
            {
                "city": "果洛藏族自治州",
                "name": "玛沁县",
                "id": "632621"
            },
            {
                "city": "果洛藏族自治州",
                "name": "班玛县",
                "id": "632622"
            },
            {
                "city": "果洛藏族自治州",
                "name": "甘德县",
                "id": "632623"
            },
            {
                "city": "果洛藏族自治州",
                "name": "达日县",
                "id": "632624"
            },
            {
                "city": "果洛藏族自治州",
                "name": "久治县",
                "id": "632625"
            },
            {
                "city": "果洛藏族自治州",
                "name": "玛多县",
                "id": "632626"
            }
        ],
        "632700": [
            {
                "city": "玉树藏族自治州",
                "name": "玉树市",
                "id": "632701"
            },
            {
                "city": "玉树藏族自治州",
                "name": "杂多县",
                "id": "632722"
            },
            {
                "city": "玉树藏族自治州",
                "name": "称多县",
                "id": "632723"
            },
            {
                "city": "玉树藏族自治州",
                "name": "治多县",
                "id": "632724"
            },
            {
                "city": "玉树藏族自治州",
                "name": "囊谦县",
                "id": "632725"
            },
            {
                "city": "玉树藏族自治州",
                "name": "曲麻莱县",
                "id": "632726"
            }
        ],
        "632800": [
            {
                "city": "海西蒙古族藏族自治州",
                "name": "格尔木市",
                "id": "632801"
            },
            {
                "city": "海西蒙古族藏族自治州",
                "name": "德令哈市",
                "id": "632802"
            },
            {
                "city": "海西蒙古族藏族自治州",
                "name": "乌兰县",
                "id": "632821"
            },
            {
                "city": "海西蒙古族藏族自治州",
                "name": "都兰县",
                "id": "632822"
            },
            {
                "city": "海西蒙古族藏族自治州",
                "name": "天峻县",
                "id": "632823"
            }
        ],
        "640100": [
            {
                "city": "银川市",
                "name": "市辖区",
                "id": "640101"
            },
            {
                "city": "银川市",
                "name": "兴庆区",
                "id": "640104"
            },
            {
                "city": "银川市",
                "name": "西夏区",
                "id": "640105"
            },
            {
                "city": "银川市",
                "name": "金凤区",
                "id": "640106"
            },
            {
                "city": "银川市",
                "name": "永宁县",
                "id": "640121"
            },
            {
                "city": "银川市",
                "name": "贺兰县",
                "id": "640122"
            },
            {
                "city": "银川市",
                "name": "灵武市",
                "id": "640181"
            }
        ],
        "640200": [
            {
                "city": "石嘴山市",
                "name": "市辖区",
                "id": "640201"
            },
            {
                "city": "石嘴山市",
                "name": "大武口区",
                "id": "640202"
            },
            {
                "city": "石嘴山市",
                "name": "惠农区",
                "id": "640205"
            },
            {
                "city": "石嘴山市",
                "name": "平罗县",
                "id": "640221"
            }
        ],
        "640300": [
            {
                "city": "吴忠市",
                "name": "市辖区",
                "id": "640301"
            },
            {
                "city": "吴忠市",
                "name": "利通区",
                "id": "640302"
            },
            {
                "city": "吴忠市",
                "name": "红寺堡区",
                "id": "640303"
            },
            {
                "city": "吴忠市",
                "name": "盐池县",
                "id": "640323"
            },
            {
                "city": "吴忠市",
                "name": "同心县",
                "id": "640324"
            },
            {
                "city": "吴忠市",
                "name": "青铜峡市",
                "id": "640381"
            }
        ],
        "640400": [
            {
                "city": "固原市",
                "name": "市辖区",
                "id": "640401"
            },
            {
                "city": "固原市",
                "name": "原州区",
                "id": "640402"
            },
            {
                "city": "固原市",
                "name": "西吉县",
                "id": "640422"
            },
            {
                "city": "固原市",
                "name": "隆德县",
                "id": "640423"
            },
            {
                "city": "固原市",
                "name": "泾源县",
                "id": "640424"
            },
            {
                "city": "固原市",
                "name": "彭阳县",
                "id": "640425"
            }
        ],
        "640500": [
            {
                "city": "中卫市",
                "name": "市辖区",
                "id": "640501"
            },
            {
                "city": "中卫市",
                "name": "沙坡头区",
                "id": "640502"
            },
            {
                "city": "中卫市",
                "name": "中宁县",
                "id": "640521"
            },
            {
                "city": "中卫市",
                "name": "海原县",
                "id": "640522"
            }
        ],
        "650100": [
            {
                "city": "乌鲁木齐市",
                "name": "市辖区",
                "id": "650101"
            },
            {
                "city": "乌鲁木齐市",
                "name": "天山区",
                "id": "650102"
            },
            {
                "city": "乌鲁木齐市",
                "name": "沙依巴克区",
                "id": "650103"
            },
            {
                "city": "乌鲁木齐市",
                "name": "新市区",
                "id": "650104"
            },
            {
                "city": "乌鲁木齐市",
                "name": "水磨沟区",
                "id": "650105"
            },
            {
                "city": "乌鲁木齐市",
                "name": "头屯河区",
                "id": "650106"
            },
            {
                "city": "乌鲁木齐市",
                "name": "达坂城区",
                "id": "650107"
            },
            {
                "city": "乌鲁木齐市",
                "name": "米东区",
                "id": "650109"
            },
            {
                "city": "乌鲁木齐市",
                "name": "乌鲁木齐县",
                "id": "650121"
            }
        ],
        "650200": [
            {
                "city": "克拉玛依市",
                "name": "市辖区",
                "id": "650201"
            },
            {
                "city": "克拉玛依市",
                "name": "独山子区",
                "id": "650202"
            },
            {
                "city": "克拉玛依市",
                "name": "克拉玛依区",
                "id": "650203"
            },
            {
                "city": "克拉玛依市",
                "name": "白碱滩区",
                "id": "650204"
            },
            {
                "city": "克拉玛依市",
                "name": "乌尔禾区",
                "id": "650205"
            }
        ],
        "650400": [
            {
                "city": "吐鲁番市",
                "name": "高昌区",
                "id": "650402"
            },
            {
                "city": "吐鲁番市",
                "name": "鄯善县",
                "id": "650421"
            },
            {
                "city": "吐鲁番市",
                "name": "托克逊县",
                "id": "650422"
            }
        ],
        "650500": [
            {
                "city": "哈密市",
                "name": "伊州区",
                "id": "650502"
            },
            {
                "city": "哈密市",
                "name": "巴里坤哈萨克自治县",
                "id": "650521"
            },
            {
                "city": "哈密市",
                "name": "伊吾县",
                "id": "650522"
            }
        ],
        "652300": [
            {
                "city": "昌吉回族自治州",
                "name": "昌吉市",
                "id": "652301"
            },
            {
                "city": "昌吉回族自治州",
                "name": "阜康市",
                "id": "652302"
            },
            {
                "city": "昌吉回族自治州",
                "name": "呼图壁县",
                "id": "652323"
            },
            {
                "city": "昌吉回族自治州",
                "name": "玛纳斯县",
                "id": "652324"
            },
            {
                "city": "昌吉回族自治州",
                "name": "奇台县",
                "id": "652325"
            },
            {
                "city": "昌吉回族自治州",
                "name": "吉木萨尔县",
                "id": "652327"
            },
            {
                "city": "昌吉回族自治州",
                "name": "木垒哈萨克自治县",
                "id": "652328"
            }
        ],
        "652700": [
            {
                "city": "博尔塔拉蒙古自治州",
                "name": "博乐市",
                "id": "652701"
            },
            {
                "city": "博尔塔拉蒙古自治州",
                "name": "阿拉山口市",
                "id": "652702"
            },
            {
                "city": "博尔塔拉蒙古自治州",
                "name": "精河县",
                "id": "652722"
            },
            {
                "city": "博尔塔拉蒙古自治州",
                "name": "温泉县",
                "id": "652723"
            }
        ],
        "652800": [
            {
                "city": "巴音郭楞蒙古自治州",
                "name": "库尔勒市",
                "id": "652801"
            },
            {
                "city": "巴音郭楞蒙古自治州",
                "name": "轮台县",
                "id": "652822"
            },
            {
                "city": "巴音郭楞蒙古自治州",
                "name": "尉犁县",
                "id": "652823"
            },
            {
                "city": "巴音郭楞蒙古自治州",
                "name": "若羌县",
                "id": "652824"
            },
            {
                "city": "巴音郭楞蒙古自治州",
                "name": "且末县",
                "id": "652825"
            },
            {
                "city": "巴音郭楞蒙古自治州",
                "name": "焉耆回族自治县",
                "id": "652826"
            },
            {
                "city": "巴音郭楞蒙古自治州",
                "name": "和静县",
                "id": "652827"
            },
            {
                "city": "巴音郭楞蒙古自治州",
                "name": "和硕县",
                "id": "652828"
            },
            {
                "city": "巴音郭楞蒙古自治州",
                "name": "博湖县",
                "id": "652829"
            }
        ],
        "652900": [
            {
                "city": "阿克苏地区",
                "name": "阿克苏市",
                "id": "652901"
            },
            {
                "city": "阿克苏地区",
                "name": "温宿县",
                "id": "652922"
            },
            {
                "city": "阿克苏地区",
                "name": "库车县",
                "id": "652923"
            },
            {
                "city": "阿克苏地区",
                "name": "沙雅县",
                "id": "652924"
            },
            {
                "city": "阿克苏地区",
                "name": "新和县",
                "id": "652925"
            },
            {
                "city": "阿克苏地区",
                "name": "拜城县",
                "id": "652926"
            },
            {
                "city": "阿克苏地区",
                "name": "乌什县",
                "id": "652927"
            },
            {
                "city": "阿克苏地区",
                "name": "阿瓦提县",
                "id": "652928"
            },
            {
                "city": "阿克苏地区",
                "name": "柯坪县",
                "id": "652929"
            }
        ],
        "653000": [
            {
                "city": "克孜勒苏柯尔克孜自治州",
                "name": "阿图什市",
                "id": "653001"
            },
            {
                "city": "克孜勒苏柯尔克孜自治州",
                "name": "阿克陶县",
                "id": "653022"
            },
            {
                "city": "克孜勒苏柯尔克孜自治州",
                "name": "阿合奇县",
                "id": "653023"
            },
            {
                "city": "克孜勒苏柯尔克孜自治州",
                "name": "乌恰县",
                "id": "653024"
            }
        ],
        "653100": [
            {
                "city": "喀什地区",
                "name": "喀什市",
                "id": "653101"
            },
            {
                "city": "喀什地区",
                "name": "疏附县",
                "id": "653121"
            },
            {
                "city": "喀什地区",
                "name": "疏勒县",
                "id": "653122"
            },
            {
                "city": "喀什地区",
                "name": "英吉沙县",
                "id": "653123"
            },
            {
                "city": "喀什地区",
                "name": "泽普县",
                "id": "653124"
            },
            {
                "city": "喀什地区",
                "name": "莎车县",
                "id": "653125"
            },
            {
                "city": "喀什地区",
                "name": "叶城县",
                "id": "653126"
            },
            {
                "city": "喀什地区",
                "name": "麦盖提县",
                "id": "653127"
            },
            {
                "city": "喀什地区",
                "name": "岳普湖县",
                "id": "653128"
            },
            {
                "city": "喀什地区",
                "name": "伽师县",
                "id": "653129"
            },
            {
                "city": "喀什地区",
                "name": "巴楚县",
                "id": "653130"
            },
            {
                "city": "喀什地区",
                "name": "塔什库尔干塔吉克自治县",
                "id": "653131"
            }
        ],
        "653200": [
            {
                "city": "和田地区",
                "name": "和田市",
                "id": "653201"
            },
            {
                "city": "和田地区",
                "name": "和田县",
                "id": "653221"
            },
            {
                "city": "和田地区",
                "name": "墨玉县",
                "id": "653222"
            },
            {
                "city": "和田地区",
                "name": "皮山县",
                "id": "653223"
            },
            {
                "city": "和田地区",
                "name": "洛浦县",
                "id": "653224"
            },
            {
                "city": "和田地区",
                "name": "策勒县",
                "id": "653225"
            },
            {
                "city": "和田地区",
                "name": "于田县",
                "id": "653226"
            },
            {
                "city": "和田地区",
                "name": "民丰县",
                "id": "653227"
            }
        ],
        "654000": [
            {
                "city": "伊犁哈萨克自治州",
                "name": "伊宁市",
                "id": "654002"
            },
            {
                "city": "伊犁哈萨克自治州",
                "name": "奎屯市",
                "id": "654003"
            },
            {
                "city": "伊犁哈萨克自治州",
                "name": "霍尔果斯市",
                "id": "654004"
            },
            {
                "city": "伊犁哈萨克自治州",
                "name": "伊宁县",
                "id": "654021"
            },
            {
                "city": "伊犁哈萨克自治州",
                "name": "察布查尔锡伯自治县",
                "id": "654022"
            },
            {
                "city": "伊犁哈萨克自治州",
                "name": "霍城县",
                "id": "654023"
            },
            {
                "city": "伊犁哈萨克自治州",
                "name": "巩留县",
                "id": "654024"
            },
            {
                "city": "伊犁哈萨克自治州",
                "name": "新源县",
                "id": "654025"
            },
            {
                "city": "伊犁哈萨克自治州",
                "name": "昭苏县",
                "id": "654026"
            },
            {
                "city": "伊犁哈萨克自治州",
                "name": "特克斯县",
                "id": "654027"
            },
            {
                "city": "伊犁哈萨克自治州",
                "name": "尼勒克县",
                "id": "654028"
            }
        ],
        "654200": [
            {
                "city": "塔城地区",
                "name": "塔城市",
                "id": "654201"
            },
            {
                "city": "塔城地区",
                "name": "乌苏市",
                "id": "654202"
            },
            {
                "city": "塔城地区",
                "name": "额敏县",
                "id": "654221"
            },
            {
                "city": "塔城地区",
                "name": "沙湾县",
                "id": "654223"
            },
            {
                "city": "塔城地区",
                "name": "托里县",
                "id": "654224"
            },
            {
                "city": "塔城地区",
                "name": "裕民县",
                "id": "654225"
            },
            {
                "city": "塔城地区",
                "name": "和布克赛尔蒙古自治县",
                "id": "654226"
            }
        ],
        "654300": [
            {
                "city": "阿勒泰地区",
                "name": "阿勒泰市",
                "id": "654301"
            },
            {
                "city": "阿勒泰地区",
                "name": "布尔津县",
                "id": "654321"
            },
            {
                "city": "阿勒泰地区",
                "name": "富蕴县",
                "id": "654322"
            },
            {
                "city": "阿勒泰地区",
                "name": "福海县",
                "id": "654323"
            },
            {
                "city": "阿勒泰地区",
                "name": "哈巴河县",
                "id": "654324"
            },
            {
                "city": "阿勒泰地区",
                "name": "青河县",
                "id": "654325"
            },
            {
                "city": "阿勒泰地区",
                "name": "吉木乃县",
                "id": "654326"
            }
        ],
        "659000": [
            {
                "city": "自治区直辖县级行政区划",
                "name": "石河子市",
                "id": "659001"
            },
            {
                "city": "自治区直辖县级行政区划",
                "name": "阿拉尔市",
                "id": "659002"
            },
            {
                "city": "自治区直辖县级行政区划",
                "name": "图木舒克市",
                "id": "659003"
            },
            {
                "city": "自治区直辖县级行政区划",
                "name": "五家渠市",
                "id": "659004"
            },
            {
                "city": "自治区直辖县级行政区划",
                "name": "铁门关市",
                "id": "659006"
            }
        ]
    }
}

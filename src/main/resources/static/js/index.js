let paramsValue = ""
$(function () {
    window.setInterval(reinitIframe, 100);
    paramsValue = getParams();
    getUserInfo();
    /*if (paramsValue != "" && paramsValue != undefined) {
        getUserInfo();
    } else {
        console.log("9999")
    }*/
});

//页面加载时发送请求,加载用户信息
function getUserInfo() {
    $.ajax({
        url: "/user/findUserById",
        data: {"id": "paramsValue.id"},
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        type: "POST",
        success: function (data) {
            if (data.rtCode == "0") {
                $("#showName").css("display", "block");
                $("#nameLabel").html(data.data.nickname);
                $("#showLogin").css("display", "none");
            } else if (data.rtCode == "-9999") {
                toastr.info(data.rtMsg);
            } else {

            }
        },
        error: function (jqXHR) {
            toastr.info("发生错误：" + jqXHR.status);
        }
    });
}

//获取a标签href所传的值
function getParams() {
    try {
        let url = window.location.href;
        let result = url.split("?")[1];
        let k_v = result.split("&");
        let obj = {};
        for (var i = 0; i < k_v.length; i++) {
            var item = k_v[i].split("=");
            obj[item[0]] = item[1];
            return obj
        }
    } catch (e) {
        // console.warn("There has no param and value!");
    }
}

//iframe高度控制
function reinitIframe() {
    let iframe = document.getElementById("iframep");
    try {
        let bHeight = iframe.contentWindow.document.body.scrollHeight;
        let dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
        let height = Math.max(bHeight, dHeight);
        iframe.height = height;
    } catch (ex) {
    }
}

//菜单图标更换
function showPersonMenu(param) {
    // console.log($("#person_icon").attr("class"));
    if (param == 'person') {
        $("#personal_menu").slideToggle(
            function () {
                ($("#person_icon").attr("class") == "glyphicon glyphicon-triangle-top") ?
                    ($("#person_icon").attr("class", "glyphicon glyphicon-triangle-bottom")) : ($("#person_icon").attr("class",
                    "glyphicon glyphicon-triangle-top"));
            }
        );
    } else if (param == 'family') {
        $("#family_menu").slideToggle(
            function () {
                ($("#family_icon").attr("class") == "glyphicon glyphicon-triangle-top") ?
                    ($("#family_icon").attr("class", "glyphicon glyphicon-triangle-bottom")) : ($("#family_icon").attr("class",
                    "glyphicon glyphicon-triangle-top"));
            }
        );
    } else if (param == 'income') {
        $("#income_menu").slideToggle(
            function () {
                ($("#income_icon").attr("class") == "glyphicon glyphicon-triangle-top") ?
                    ($("#income_icon").attr("class", "glyphicon glyphicon-triangle-bottom")) : ($("#income_icon").attr("class",
                    "glyphicon glyphicon-triangle-top"));
            }
        );
    } else if (param == 'menu4') {
        $("#menu4_menu").slideToggle(
            function () {
                ($("#menu4_icon").attr("class") == "glyphicon glyphicon-triangle-top") ?
                    ($("#menu4_icon").attr("class", "glyphicon glyphicon-triangle-bottom")) : ($("#menu4_icon").attr("class",
                    "glyphicon glyphicon-triangle-top"));
            }
        );
    } else if (param == 'menu5') {
        $("#menu5_menu").slideToggle(
            function () {
                ($("#menu5_icon").attr("class") == "glyphicon glyphicon-triangle-top") ?
                    ($("#menu5_icon").attr("class", "glyphicon glyphicon-triangle-bottom")) : ($("#menu5_icon").attr("class",
                    "glyphicon glyphicon-triangle-top"));
            }
        );
    }
}

//加载修改个人信息页面
function load_update() {
    $("#iframep").attr("src", "/user/update");
}

// 退出登录
function log_out() {
    $.ajax({
        url: "/user/logout",
        // data: {"id": "paramsValue.id"},
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        type: "POST",
        success: function (data) {
            if (data.rtCode == "0") {
                toastr.success("退出登录成功");
                window.location.href = "/user/index";
            } else {
                toastr.info("操作失败");
            }
        },
        error: function (jqXHR) {
            toastr.info("发生错误：" + jqXHR.status);
        }
    });
}
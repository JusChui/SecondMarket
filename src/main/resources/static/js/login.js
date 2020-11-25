$(function () {
    $("#loginBtn").on('click', login);
});

var login = function () {
    var dataq = {
        'id': '',
        'email': '',
        'telNum': '',
        'password': $('#exampleInputPassword1').val()
    };
    var num = $("#exampleInputEmail1").val();
    if (checkData(num) == "error") {
        toastr.info("输入帐号格式有误,请输入手机号或邮箱账号！")
    } else {
        if (checkData(num) == "email") {
            dataq.email = num;
        }
        if (checkData(num) == "tel") {
            dataq.telNum = num;
        }
        $.ajax({
            type: "POST",
            url: "/user/findUser",
            dataType: "json", //预期服务器返回数据的类型
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(dataq),
            success: function (data) {
                // console.log(JSON.stringify(data));
                if (data.id == null) {
                    toastr.info("帐号未注册!");
                } else if (data.password == dataq.password) {
                    toastr.info("Success");
                    window.location.href = '/user/index';
                    // window.location.href = '/user/index?id=' + data.id;
                } else {
                    toastr.info("账号和密码不匹配");
                }
            },
            error: function (jqXHR) {
                toastr.info("发生错误：" + jqXHR.status);
            },
        });
    }

    return false;
}

function checkData(num) {
    let emailReg = /^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
    let telReg = /^0?1[3|4|5|6|7|8|9][0-9]\d{8}$/;
    if (emailReg.test(num)) {
        return "email";
    } else if (telReg.test(num)) {
        return "tel";
    } else {
        return "error";
    }
}
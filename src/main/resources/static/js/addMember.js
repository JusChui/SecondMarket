$(function () {
    addMemberEventInit();
});

function query() {
    let dataq = {
        "email": "",
        "telNum": ""
    }
    let num = $("#param").val();
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
                if (data.id == null || data.id == "") {
                    toastr.info("帐号不存在")
                } else {
                    $("#nickname").html(data.nickname);
                    $("#name").html(data.username);
                    $("#email").html(data.email);
                    $("#tel").html(data.telNum);
                    $("#birthday").html(data.birthday);
                    $("#gender").html(data.gender);
                    $("#result").css("display", 'block');
                }
            },
            error: function (jqXHR) {
                toastr.info("发生错误：" + jqXHR.status);
            },
        });
    }
}

function addMemberEventInit() {
    $("#q").on('click', query);
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
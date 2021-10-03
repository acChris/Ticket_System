

$('#addTicket').click(function () {
    var rentTicket_from = $('#rentTicket_from').val();
    var rentTicket_to = $('#rentTicket_to').val();
    var rentTicket_count = $('#rentTicket_count').val();
    // 判空
    if (isNull(rentTicket_from)) {
        swal("请输入车票起点", {
            icon: "error",
        });
        return;
    }
    if (isNull(rentTicket_to)) {
        swal("请输入车票终点", {
            icon: "error",
        });
        return;
    }
    if (isNull(rentTicket_count)) {
        swal("请输入车票总数", {
            icon: "error",
        });
        return;
    }

    // 判长度
    if (!validLength(rentTicket_from, 50)) {
        swal("起点名字过长", {
            icon: "error",
        });
        return;
    }
    if (!validLength(rentTicket_to, 50)) {
        swal("终点名字过长", {
            icon: "error",
        });
        return;
    }
    if (!validLength(rentTicket_count, 22)) {
        swal("车票总数过长", {
            icon: "error",
        });
        return;
    }
    $('#articleModal').modal('show');
});

$('#saveButton').click(function () {
    var url = '/admin/ticket/save';
    var swlMessage = '保存成功';
    var data = {
        "rentTicket_from": rentTicket_from,
        "rentTicket_to": rentTicket_to,
        "rentTicket_count": rentTicket_count
    };
    // if (blogId > 0) {
    //     url = '/admin/ticket/update';
    //     swlMessage = '修改成功';
    //     data = {
    //         "blogId": blogId,
    //         "blogTitle": blogTitle,
    //         "blogSubUrl": blogSubUrl
    //     };
    // }
    console.log(data);
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: data,
        success: function (result) {
            if (result.resultCode === 200) {
                $('#articleModal').modal('hide');
                console.log('into success!')
                swal({
                    title: swlMessage,
                    type: 'success',
                    showCancelButton: false,
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: '返回订票列表',
                    confirmButtonClass: 'btn btn-success',
                    buttonsStyling: false
                }).then(function () {
                    window.location.href = "/admin/ticket";
                })
            }
            else {
                $('#articleModal').modal('hide');
                swal(result.message, {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });
});

$('#cancelButton').click(function () {
    window.location.href = "/admin/ticket";
});



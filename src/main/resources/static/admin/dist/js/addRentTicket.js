

$('#addTicket').click(function () {
    var rentTicketFrom = $('#rentTicketFrom').val();
    var rentTicketTo = $('#rentTicketTo').val();
    var rentTicketCount = $('#rentTicketCount').val();
    var startTime = $('#startTime').val();
    var endTime = $('#endTime').val();
    // 判空
    if (isNull(rentTicketFrom)) {
        swal("请输入车票起点", {
            icon: "error",
        });
        return;
    }
    if (isNull(rentTicketTo)) {
        swal("请输入车票终点", {
            icon: "error",
        });
        return;
    }
    if (isNull(rentTicketCount)) {
        swal("请输入车票总数", {
            icon: "error",
        });
        return;
    }

    // 判长度
    if (!validCN_ENString2_18(rentTicketFrom) || !validLength(rentTicketFrom, 50)) {
        swal("起点名字过长", {
            icon: "error",
        });
        return;
    }
    if (!validCN_ENString2_18(rentTicketTo) || !validLength(rentTicketTo, 50)) {
        swal("终点名字过长", {
            icon: "error",
        });
        return;
    }
    if (!validCN_ENString2_18(rentTicketCount) || !validLength(rentTicketCount, 22)) {
        swal("车票总数过长", {
            icon: "error",
        });
        return;
    }
    $('#addRentTicketModal').modal('show');
});

$('#saveButton').click(function () {
    // 输入数据
    var rentTicketFrom = $('#rentTicketFrom').val();
    var rentTicketTo = $('#rentTicketTo').val();
    var rentTicketCount = $('#rentTicketCount').val();
    var startTime = $('#startTime').val();
    var endTime = $('#endTime').val();

    var url = '/admin/rentTicket/save';
    var swlMessage = '保存成功';
    var data = {
        "rentTicketFrom": rentTicketFrom,
        "rentTicketTo": rentTicketTo,
        "startTime": startTime,
        "endTime": endTime,
        "rentTicketCount": rentTicketCount
    };
    /*var rentTicketId = getSelectedRow();
    if (rentTicketId > 0) {
        url = '/admin/ticket/update';
        swlMessage = '修改成功';
        data = {
            "id": rentTicketId,
            "rentTicketFrom": rentTicketFrom,
            "rentTicketTo": rentTicketTo,
            "rentTicketCount": rentTicketCount
        };
    }*/
    console.log(data);
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: data,
        success: function (result) {
            if (result.resultCode === 200) {
                $('#addRentTicketModal').modal('hide');
                console.log('addRentTicket success on addRentTicket.js!')
                swal({
                    title: swlMessage,
                    type: 'success',
                    showCancelButton: false,
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: '返回订票列表',
                    confirmButtonClass: 'btn btn-success',
                    buttonsStyling: false
                }).then(function () {
                    window.location.href = "/admin/rentTicket";
                })
            }
            else {
                $('#addRentTicketModal').modal('hide');
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



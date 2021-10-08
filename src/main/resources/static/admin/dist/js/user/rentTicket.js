$(function () {
    $("#jqGrid").jqGrid({
        url: '/user/rentTicket/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: '出发点',       name: 'rentTicketFrom', index: 'rentTicketFrom', width: 80},
            {label: '终点',         name: 'rentTicketTo', index: 'rentTicketTo', width: 80},
            {label: '出发时间',     name: 'startTime', index: 'startTime', width: 60, },
            {label: '到达时间',     name: 'endTime', index: 'endTime', width: 60, },
            {label: '余票数',       name: 'rentTicketCount', index: 'rentTicketCount', width: 60, }
        ],
        height: 700,                // 高
        rowNum: 10,                 // 行数 
        rowList: [10, 20, 50],      // 可选行数
        styleUI: 'Bootstrap',       // 样式UI
        loadtext: '信息读取中...',   // 加载信息
        rownumbers: false,          // 是否显示行序号（true显示）
        rownumWidth: 20,            // 列宽
        autowidth: true,            // 自动调整宽度
        multiselect: true,          // 是否可以多选

        // 设置表格控件
        pager: "#jqGridPager",
        // JSON 数据格式的数组
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    
    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

    // 图片格式代码
    /* function coverImageFormatter(cellvalue) {
            return "<img src='" + cellvalue + "' height=\"120\" width=\"160\" alt='coverImage'/>";
        }*/

    

});

/**
 * 搜索功能
 */
function search() {
    //标题关键字
    var keyword = $('#keyword').val();
    if (!validLength(keyword, 20)) {
        swal("搜索字段长度过大!", {
            icon: "error",
        });
        return false;
    }
    //数据封装
    var searchData = {keyword: keyword};
    //传入查询条件参数
    $("#jqGrid").jqGrid("setGridParam", {postData: searchData});
    //点击搜索按钮默认都从第一页开始
    $("#jqGrid").jqGrid("setGridParam", {page: 1});
    //提交post并刷新表格
    $("#jqGrid").jqGrid("setGridParam", {url: '/user/rentTicket/list'}).trigger("reloadGrid");
}

/**
 * jqGrid重新加载
 */
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

function orderTicket() {
    $('.modal-title').html('订票信息');
    $('#rentTicketModal').modal('show');

    var id = getSelectedRow();

    // 向后端发出 GET 请求，根据 id 获取数据
    $.get("/user/rentTicket/edit/" + id, function (r) {
        if (r.resultCode == 200 && r.data != null) {
            // var sTime = stringToDate(r.data.startTime);
            // var eTime = stringToDate(r.data.endTime);
            // console.log(r.data.startTime.sub(0, r.data.startTime.length - 3));
            // console.log("sTime" + sTime);
            // console.log("eTime" + eTime);
            // console.log(r.data.startTime.sub());
            // console.log(r.data.endTime);
            // 转换日期
            var sTime = toPageDateTime(r.data.startTime);
            var eTime = toPageDateTime(r.data.endTime);

            //填充数据至modal
            $("#id").val(id);
            $("#rentTicketFrom").val(r.data.rentTicketFrom);
            $("#rentTicketTo").val(r.data.rentTicketTo);
            $("#startTime").val(sTime);
            $("#endTime").val(eTime);
        }
    });
    // window.location.href = "";
}


function saveButton() {
    var id =                getSelectedRow();
    var rentTicketFrom  = $('#rentTicketFrom').val();
    var rentTicketTo    = $('#rentTicketTo').val();
    var startTime       = toTotalDate($('#startTime').val());
    var endTime         = toTotalDate($('#endTime').val());

    console.log(startTime + "  " + endTime);
    if (id == null) {
        return;
    }
    var params={
        "rentTicketId": id,
        "rentTicketFrom": rentTicketFrom,
        "rentTicketTo": rentTicketTo,
        "startTime": startTime,
        "endTime": endTime,
    };
    $.ajax({
        type: 'POST',//方法类型
        url: '/user/rentTicket/add',
        data: params,
        success: function (result) {
            if (result.resultCode === 200) {
                $('#rentTicketModal').modal('hide');
                swal("保存成功", {
                    icon: "success",
                });
                reload();
            }
            else {
                $('#rentTicketModal').modal('hide');
                // console.log(result.resultCode);
                // console.log(params);
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
}

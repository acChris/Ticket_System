$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/ticket/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: '出发点', name: 'rent_ticket_from', index: 'rent_ticket_from', width: 80},
            {label: '终点', name: 'rent_ticket_to', index: 'rent_ticket_to', width: 80},
            // formatter: coverImageFormatter
            {label: '出发时间', name: 'start_time', index: 'start_time', width: 60, },
            {label: '到达时间', name: 'end_time', index: 'end_time', width: 60, },
            {label: '余票数', name: 'rent_ticket_count', index: 'rent_ticket_count', width: 60, }
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

    function statusFormatter(cellvalue) {
        if (cellvalue == 0) {
            return "<button type=\"button\" class=\"btn btn-block btn-secondary btn-sm\" style=\"width: 50%;\">待支付</button>";
        }
        else if (cellvalue == 1) {
            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm\" style=\"width: 50%;\">订票成功</button>";
        }
    }

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
    $("#jqGrid").jqGrid("setGridParam", {url: '/admin/ticket/list'}).trigger("reloadGrid");
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

function addTicket() {
    window.location.href = "/admin/ticket/add";
}

function editTicket() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    window.location.href = "/admin/ticket/edit/" + id;
}

function deleteTicket() {
    var ids = getSelectedRows();    // 获取选中行
    if (ids == null) {
        return;
    }
    swal({  // 弹出框
        title: "确认弹框",          // 标题
        text: "确认要删除数据吗?",  // 显示文本
        icon: "warning",           // 警告图标
        buttons: true,             // 按钮
        dangerMode: true,          // 启动危险模式
    }).then((flag) => {
            if (flag) {
                $.ajax({           // ajax用于前后端数据交互
                    type: "POST",
                    url: "/admin/ticket/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {    
                        if (r.resultCode == 200) {
                            swal("删除成功", {
                                icon: "success",    
                            });
                            // 重载表格
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(r.message, {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        }
    );
}
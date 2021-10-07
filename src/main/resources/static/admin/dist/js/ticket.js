$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/ticket/list',
        datatype: "json",
        colModel: [
            {label: 'id',      name: 'id',          index: 'id', width: 50, key: true, hidden: true},
            {label: '出发点',   name: 'ticketFrom', index: 'ticketFrom', width: 60},
            {label: '终点',     name: 'ticketTo',   index: 'ticketTo', width: 60},
            {label: '购票人',   name: 'ticketPayer', index: 'ticketPayer', width: 60},
            {label: '票数',     name: 'ticketCount', index: 'ticketCount', width: 30, },
            {label: '出发时间', name: 'startTime',  index: 'startTime', width: 90, },
            {label: '到达时间', name: 'endTime',    index: 'endTime', width: 90, },
            {label: '购票时间', name: 'buyTime',    index: 'buyTime', width: 90},
            {label: '支付时间', name: 'payTime',     index: 'payTime', width: 90},
            {label: '订单状态', name: 'ticketStatus', index: 'ticketStatus', width: 60, formatter: statusFormatter,}
        ],
        height: 700,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
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

    // 设置图片选择器 并 加载已有图片
    /*jQuery("select.image-picker").imagepicker({
        hide_select: false,
    });

    jQuery("select.image-picker.show-labels").imagepicker({
        hide_select: false,
        show_label: true,
    });
    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });
    var container = jQuery("select.image-picker.masonry").next("ul.thumbnails");
    container.imagesLoaded(function () {
        container.masonry({
            itemSelector: "li",
        });
    });*/
    function statusFormatter(cellvalue) {
        if (cellvalue == 0) {
            return "<button type=\"button\" class=\"btn btn-block btn-secondary btn-sm\" style=\"width: 80%;\">待支付</button>";
        }
        else if (cellvalue == 1) {
            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm\" style=\"width: 80%;\">订票成功</button>";
        }
    }

});

// function imgFormatter(cellvalue) {
//     return "<a href='" + cellvalue + "'> <img src='" + cellvalue + "' height=\"64\" width=\"64\" alt='icon'/></a>";
// }

/**
 * jqGrid重新加载
 */
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

function ticketAdd() {
    // reset();
    $('.modal-title').html('车票添加');
    $('#ticketModal').modal('show');
}

//绑定modal上的保存按钮
$('#saveButton').click(function () {
    var ticketId =      $('#ticketId').val();
    var ticketFrom =    $("#ticketFrom").val();
    var ticketTo =      $("#ticketTo").val();
    var ticketPayer =   $("#ticketPayer").val();
    var ticketCount =   $("#ticketCount").val();
    var startTime =     $("#startTime").val();
    var endTime =       $("#endTime").val();
    var ticketStatus =  $("#ticketStatus").val();

    if (!validCNString2_100(ticketFrom) && validCNString2_100(ticketTo)) {
    $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("请输入符合规范的名称（2-100中文）！");
    } else {
        var params = {
            "ticketId":     ticketId,
            "ticketFrom":   ticketFrom,
            "ticketTo":     ticketTo,
            "ticketPayer":  ticketPayer,
            "ticketCount":  ticketCount,
            "startTime":    startTime,
            "endTime":      endTime,
            "ticketStatus": ticketStatus
        };
        var url = '/admin/ticket/save';
        var id = getSelectedRowWithoutAlert();
        if (id != null) {
            url = '/admin/ticket/edit/' + ticketId;
        }
        console.log(params);
        $.ajax({
            type: 'POST',//方法类型
            url: url,
            data: params,
            success: function (result) {
                if (result.resultCode == 200) {
                    $('#ticketModal').modal('hide');
                    swal("保存成功", {
                        icon: "success",
                    });
                    reload();
                }
                else {
                    $('#ticketModal').modal('hide');
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
});

function ticketEdit() {
    // reset();
    var ticketId =      getSelectedRows();

    if (ticketId == null) {
        return;
    }
    $('.modal-title').html('用户购票编辑');
    $('#ticketModal').modal('show');


    $.get("/admin/ticket/edit/" + ticketId, function (r) {
        if (r.resultCode == 200 && r.data != null) {
            //填充数据至modal
            $("#ticketId").val(r.data.id);
            $("#ticketFrom").val(r.data.ticketFrom);
            $("#ticketTo").val(r.data.ticketTo);
            $("#ticketPayer").val(r.data.ticketPayer);
            $("#ticketCount").val(r.data.ticketFrom.ticketCount);
            $("#startTime").val(r.data.ticketFrom.startTime);
            $("#endTime").val(r.data.ticketFrom.endTime);
        }
    });
}

function ticketDelete() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要删除数据吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/ticket/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.resultCode == 200) {
                            swal("删除成功", {
                                icon: "success",
                            });
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

/**
 * 搜索功能
 */
 function ticketSearch() {
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

/* $('#datetimepicker1').datetimepicker({
 
      // requires moment-timezone.js
      timeZone: '',
     
      // date format
      // http://momentjs.com/docs/#/displaying/format/
      format: false,
      dayViewHeaderFormat: 'MMMM YYYY',
      extraFormats: false,
     
      // step size
      stepping: 1,
     
      // min/max dates
      minDate: false,
      maxDate: false,
     
      // uses current date/time
      useCurrent: true,
     
      // uses Bootstraps collapse to switch between date/time pickers
      collapse: true,
     
      // https://github.com/moment/moment/tree/develop/locale
      locale: moment.locale(),
     
      // default date
      defaultDate: false,
     
      // disabled dates
      // array of [date, moment, string]
      disabledDates: false,
     
      // enabled dates
      // array of [date, moment, string]
      enabledDates: false,
     
      // default icons
      icons: {
        time: 'fa fa-clock-o',
        date: 'fa fa-calendar',
        up: 'fa fa-arrow-up',
        down: 'fa fa-arrow-down',
        previous: 'fa fa-chevron-left',
        next: 'fa fa-chevron-right',
        today: 'fa fa-calendar-check-o',
        clear: 'fa fa-delete',
        close: 'fa fa-times'
      },
     
      // tooltip options
      tooltips: {
        today: 'Go to today',
        clear: 'Clear selection',
        close: 'Close the picker',
        selectMonth: 'Select Month',
        prevMonth: 'Previous Month',
        nextMonth: 'Next Month',
        selectYear: 'Select Year',
        prevYear: 'Previous Year',
        nextYear: 'Next Year',
        selectDecade: 'Select Decade',
        prevDecade: 'Previous Decade',
        nextDecade: 'Next Decade',
        prevCentury: 'Previous Century',
        nextCentury: 'Next Century',
        pickHour: 'Pick Hour',
        incrementHour: 'Increment Hour',
        decrementHour: 'Decrement Hour',
        pickMinute: 'Pick Minute',
        incrementMinute: 'Increment Minute',
        decrementMinute: 'Decrement Minute',
        pickSecond: 'Pick Second',
        incrementSecond: 'Increment Second',
        decrementSecond: 'Decrement Second',
        togglePeriod: 'Toggle Period',
        selectTime: 'Select Time',
        selectDate: 'Select Date'
      },
     
      // uses strict
      useStrict: false,
     
      // displays side by side
      sideBySide: false,
     
      // disabled days of the week
      daysOfWeekDisabled: false,
     
      // shows the week of the year
      calendarWeeks: false,
     
      // 'decades','years','months','days'
      viewMode: 'days',
     
      // toolbar placement
      toolbarPlacement: 'default',
     
      // enable/disable buttons
      buttons: {
        showToday: false,
        showClear: false,
        showClose: false
      },
     
      // widget position
      widgetPositioning: {
        horizontal: 'auto',
        vertical: 'auto'
      },
     
      // string or jQuery object
      widgetParent: null,
     
      // ignore read only input
      ignoreReadonly: false,
     
      // always keep open
      keepOpen: false,
     
      // shows on focus
      focusOnShow: true,
     
      // inline mode
      inline: false,
     
      // makes the date picker not revert or overwrite invalid dates
      keepInvalid: false,
     
      // debug mode
      debug: false,
     
      // shows on focus and icon click
      allowInputToggle: false,
     
      // disables time selection
      disabledTimeIntervals: false,
     
      // disables/enables hours
      disabledHours: false,
      enabledHours: false,
     
      // changes the viewDate without changing or setting the selected date
      viewDate: false,
     
      // allows multiple dates
      allowMultidate: false,
     
      // custom separator
      multidateSeparator: ','
       
    }); */
    
/* function reset() {
    $("#categoryName").val('');
    $("#categoryIcon option:first").prop("selected", 'selected');
} */
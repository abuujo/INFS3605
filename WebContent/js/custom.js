$( function() {
  		$( '#date' ).datetimepicker({
  			autoclose: true
  		});
});

$( function() {
		$( '#endDate' ).datetimepicker({
			autoclose: true
		});
});

var Msg = '<%=session.getAttribute("bookingSuccess")%>';
if(Msg != "null") {
function confirmBooking() {
	alert("Booking has been successfully added!");
	window.location.href = "http://localhost:8080/FourLeavesTech/home";
}
}

var Msg = '<%=session.getAttribute("addStudent")%>';
if(Msg != "null") {
function confirmStudent() {
	alert("Student has been successfully added");
	window.location.href = "http://localhost:8080/FourLeavesTech/studentsList";
}
}

var Msg = '<%=session.getAttribute("registered")%>';
if(Msg != "null") {
function confirmRegistration() {
	alert("Registration was successfully done!");
	window.location.href = "http://localhost:8080/FourLeavesTech/";
}
}

var Msg = '<%=session.getAttribute("recovery")%>';
if(Msg != "null") {
function confirmRecovery() {
	alert("Temporary password was sent to your email!");
	window.location.href = "http://localhost:8080/FourLeavesTech/";
}
}

function confirmSendingNotes() {
	alert('Notes was sent to the student and other attendees!');
	return true;
}

$(document).ready(function () {
    $(".btn-select").each(function (e) {
        var value = $(this).find("ul li.selected").html();
        if (value != undefined) {
            $(this).find(".btn-select-input").val(value);
            $(this).find(".btn-select-value").html(value);
        }
    });
});

$(document).on('click', '.btn-select', function (e) {
    e.preventDefault();
    var ul = $(this).find("ul");
    if ($(this).hasClass("active")) {
        if (ul.find("li").is(e.target)) {
            var target = $(e.target);
            target.addClass("selected").siblings().removeClass("selected");
            var value = target.html();
            $(this).find(".btn-select-input").val(value);
            $(this).find(".btn-select-value").html(value);
        }
        ul.hide();
        $(this).removeClass("active");
    }
    else {
        $('.btn-select').not(this).each(function () {
            $(this).removeClass("active").find("ul").hide();
        });
        ul.slideDown(300);
        $(this).addClass("active");
    }
});

$(document).on('click', function (e) {
    var target = $(e.target).closest(".btn-select");
    if (!target.length) {
        $(".btn-select").removeClass("active").find("ul").hide();
    }
});



$('#example').DataTable({
	"bPaginate" : true,
	'aoColumnDefs': [{
        'bSortable': false,
        'aTargets': [-1] /* 1st one, start by the right */
    }]
});

function confirm_status_change(imgObj, accNo) {
	console.log(imgObj.src + '   ' + accNo);
	var flag;
	var msg;
	var imgPath, curImgPath;
	var active;
	var waitImg = 'img/wait.gif';
	var deleteImgUrl = 'http://localhost:8082/SSAStateApp/img/delete_icon.jpg';
	var activeImgUrl = 'http://localhost:8082/SSAStateApp/img/active_icon.png';
	if (imgObj.src == deleteImgUrl) {
		msg = 'Account Deactivated';
		active = false;
		imgPath = 'img/active_icon.png';
		curImgPath = 'img/delete_icon.jpg';
		flag = confirm('Do you want to Deactivate account?');
		console.log('Delete clicked');
	} else if (imgObj.src == activeImgUrl) {
		msg = 'Account Activated';
		active = true;
		imgPath = 'img/delete_icon.jpg';
		curImgPath = 'img/active_icon.png';
		flag = confirm('Do you want to Activate account?');
		console.log('Active clicked');
	} else {
		console.log('Wait clicked');
		return;
	}

	if (flag == false) {
		return false;
	}
	var url = 'soft_delete/' + accNo + '/' + active + '/account';
	console.log('Ajax url : ' + url + ' Image Path : ' + imgPath);
	imgObj.src = waitImg;
	$.ajax({
		type : 'get',
		url : url,
		success : function(response) {
			if (response == "OK") {
				imgObj.src = imgPath;
				alert(msg);
				return true;
			} else {
				imgObj.src = curImgPath;
				alert('Error in ' + msg);
				return false;
			}
		}
	});

}


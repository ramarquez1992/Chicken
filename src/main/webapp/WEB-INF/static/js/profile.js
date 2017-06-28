function findImageAvatar(parent, tagname)
{
	$("#avatar").val($('#'+parent+' '+tagname)[0]['currentSrc']);
}

$("#avatar_input").fileinput({
	initialPreview: [
	    $("#imageAvatar").val()
	],
	initialPreviewAsData: true,
	initialPreviewShowDelete: false,
	showUpload: false,
	showRemove: false,
	showClose: false,
	showBrowse: false,
	showCaption: false,
	initialPreviewConfig: [
	    {caption: "Proof of Request", width: "120px", key: 1}   
	],
	initialCaption: "Proof of Request"
});
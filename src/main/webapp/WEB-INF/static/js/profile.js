function findImageAvatar(parent, tagname)
{
	$("#avatar").val($('#'+parent+' '+tagname)[0]['currentSrc']);
}

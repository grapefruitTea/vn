/*
* 1.从后台获取信息
* 2.提交信息到后台
* */
$(function () {
    var shopId = getQueryString('shopId');
    var isEdit = shopId ? true : false;
    var initUrl = '/shop/shopadmin/getshopinitinfo';
    var registerShopUrl = '/shop/shopadmin/registershop';
    var shopInfoUrl = '/shop/shopadmin/getshopbyid?shopId=' + shopId;
    var editShopUrl = '/shop/shopadmin/modifyshop';
    if (isEdit) {
        getShopInfo(shopId)
    } else {
        getShopInitInfo();
    }

    function getShopInfo(shopId) {
        $.getJSON(shopInfoUrl, function (data) {
            if (data.success) {
                var shop = data.shop;
                $('#shop-name').val(shop.shopName);
                $('#shop-addr').val(shop.shopAddr);
                $('#shop-desc').val(shop.shopDesc);
                $('#shop-phone').val(shop.phone);
                var shopCategory = '<option data-id="'
                    + shop.shopCategory.shopCategoryId + '" selected>'
                    + shop.shopCategory.shopCategoryName + '</option>';
                var tempAreaHtml = '';
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>';
                });


                $('#shop-category').html(shopCategory);
                $('#shop-category').attr("disabled", "disabled");

                $('#area').html(tempAreaHtml);
                //默认选择当前店铺属于的区域
                $("#area option[data-id='" + shop.area.areaId + "']").attr('selected', 'selected');
            }
        });

    }


    function getShopInitInfo() {
        $.getJSON(initUrl, function (data) {
            if (data.success) {
                var tempHtml = '';
                var tempAreaHtml = '';
                data.shopCategoryList.map(function (item, index) {
                    tempHtml += '<option data-id="' + item.shopCategoryId + '">'
                        + item.shopCategoryName + '</option>';
                });

                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>';
                });

                $('#shop-category').html(tempHtml);
                $('#area').html(tempAreaHtml);
            }
        });

    }

    $('#submit').click(function () {
        var shop = {};
        if (isEdit) {
            shop.shopId = shopId;
        }
        shop.shopName = $('#shop-name').val();
        shop.shopAddr = $('#shop-addr').val();
        shop.shopDesc = $('#shop-desc').val();
        shop.phone = $('#shop-phone').val();
        shop.shopCategory = {
            shopCategoryId: $('#shop-category').find('option').not(function () {
                return !this.selected;
            }).data('id')
        };
        shop.area = {
            areaId: $('#area').find('option').not(function () {
                return !this.selected;
            }).data('id')
        };
        var shopImg = $('#shop-img')[0].files[0];
        var formData = new FormData();
        var verifyCodeActual = $('#j_kaptcha').val();
        if (!verifyCodeActual) {
            $.toast('请输入验证码');
            return;
        }

        formData.append('shopImg', shopImg);
        formData.append('shopStr', JSON.stringify(shop));
        formData.append('verifyCodeActual', verifyCodeActual);

        $.ajax({
            url: (isEdit ? editShopUrl : registerShopUrl),
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.success) {
                    $.toast('提交成功！');
                } else {
                    $.toast('提交失败！' + data.errMsg);
                }
                $('#captcha_img').click();
            }
        });



    });
})
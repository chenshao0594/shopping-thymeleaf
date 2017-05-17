/* Events to trigger on every page load */
$(function(){
    // Update the locale that has been selected
    HC.updateLocaleSelection();
    // Show the JavaScript version of product options if the user has JavaScript enabled
    $('.product-options').removeClass('hidden');
    $('.product-option-nonjs').remove();

    // Bind the JavaScript product option boxes to execute on click
    $('body').on('click', '.product-option-group li:not(.unavailable)', function() {
        HC.changeProductOption($(this));
        return false;
    });
});



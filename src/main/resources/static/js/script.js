function submitform() {
    if (document.myform.onsubmit &&
        !document.myform.onsubmit()) {
        return;
    }
    document.myform.submit();
}

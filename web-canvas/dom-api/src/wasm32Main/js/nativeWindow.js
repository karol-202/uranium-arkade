konan.libraries.push({
    uranium_NativeWindow_now: () =>
        window.performance.now(),
    uranium_NativeWindow_setInterval: (timeout, handler) =>
        window.setInterval(handler, timeout),
    uranium_NativeWindow_clearInterval: (handle) =>
        window.clearInterval(handle),
    uranium_NativeWindow_setOnKeyDownListener: (handler) =>
        window.onkeydown = handler,
    uranium_NativeWindow_setOnKeyPressListener: (handler) =>
        window.onkeypress = handler,
    uranium_NativeWindow_setOnKeyUpListener: (handler) =>
        window.onkeyup = handler
});

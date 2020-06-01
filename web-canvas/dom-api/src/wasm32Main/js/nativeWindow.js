konan.libraries.push({
    uranium_NativeWindow_now: () =>
        window.performance.now(),
    uranium_NativeWindow_setInterval: (timeout, handlerIndex, handlerArena) => {
        const handler = wrapLambda(handlerArena, handlerIndex);
        window.setInterval(handler, timeout);
    },
    uranium_NativeWindow_clearInterval: (handle) =>
        window.clearInterval(handle),
    uranium_NativeWindow_setOnKeyDownListener: (handlerIndex, handlerArena) =>
        window.onkeydown = wrapLambda(handlerArena, handlerIndex),
    uranium_NativeWindow_setOnKeyPressListener: (handlerIndex, handlerArena) =>
        window.onkeypress = wrapLambda(handlerArena, handlerIndex),
    uranium_NativeWindow_setOnKeyUpListener: (handlerIndex, handlerArena) =>
        window.onkeyup = wrapLambda(handlerArena, handlerIndex)
});

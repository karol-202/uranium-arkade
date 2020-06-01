konan.libraries.push({
    uranium_getNativeCanvas: (canvasIdPtr, canvasIdLength, resultArena) => {
        const canvasId = toUTF16String(canvasIdPtr, canvasIdLength);
        const canvas = document.getElementById(canvasId);
        if(!canvas) throw Error('Canvas ' + canvasId + ' not found')
        return newArenaObject(resultArena, canvas);
    },
    uranium_NativeCanvas_getContext: (canvasArena, canvasIndex, resultArena) => {
        const canvas = kotlinObject(canvasArena, canvasIndex);
        return newArenaObject(resultArena, canvas.getContext('2d'));
    },
    uranium_NativeCanvas_getWidth: (canvasArena, canvasIndex) =>
        kotlinObject(canvasArena, canvasIndex).width,
    uranium_NativeCanvas_setWidth: (canvasArena, canvasIndex, width) =>
        kotlinObject(canvasArena, canvasIndex).width = width,
    uranium_NativeCanvas_getHeight: (canvasArena, canvasIndex) =>
        kotlinObject(canvasArena, canvasIndex).height,
    uranium_NativeCanvas_setHeight: (canvasArena, canvasIndex, height) =>
        kotlinObject(canvasArena, canvasIndex).height = height,
    uranium_NativeCanvas_getClientWidth: (canvasArena, canvasIndex) =>
        kotlinObject(canvasArena, canvasIndex).clientWidth,
    uranium_NativeCanvas_getClientHeight: (canvasArena, canvasIndex) =>
        kotlinObject(canvasArena, canvasIndex).clientHeight,
    uranium_NativeCanvas_setOnMouseDownListener: (canvasArena, canvasIndex, handler) =>
        kotlinObject(canvasArena, canvasIndex).onmousedown = handler,
    uranium_NativeCanvas_setOnMouseMoveListener: (canvasArena, canvasIndex, handler) =>
        kotlinObject(canvasArena, canvasIndex).onmousemove = handler,
    uranium_NativeCanvas_setOnMouseUpListener: (canvasArena, canvasIndex, handler) =>
        kotlinObject(canvasArena, canvasIndex).onmouseup = handler,
    uranium_NativeCanvas_setOnMouseEnterListener: (canvasArena, canvasIndex, handler) =>
        kotlinObject(canvasArena, canvasIndex).onmouseenter = handler,
    uranium_NativeCanvas_setOnMouseLeaveListener: (canvasArena, canvasIndex, handler) =>
        kotlinObject(canvasArena, canvasIndex).onmouseleave = handler,
});

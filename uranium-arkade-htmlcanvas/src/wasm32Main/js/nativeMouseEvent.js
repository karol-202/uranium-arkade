konan.libraries.push({
    uranium_NativeMouseEvent_getType: (arena, index, resultArena) => {
        const event = kotlinObject(arena, index);
        return newArenaObject(resultArena, event.type);
    },
    uranium_NativeMouseEvent_getClientX: (arena, index) =>
        kotlinObject(arena, index).clientX,
    uranium_NativeMouseEvent_getClientY: (arena, index) =>
        kotlinObject(arena, index).clientY,
    uranium_NativeMouseEvent_getScreenX: (arena, index) =>
        kotlinObject(arena, index).screenX,
    uranium_NativeMouseEvent_getScreenY: (arena, index) =>
        kotlinObject(arena, index).screenY,
    uranium_NativeMouseEvent_getMovementX: (arena, index) =>
        kotlinObject(arena, index).movementX,
    uranium_NativeMouseEvent_getMovementY: (arena, index) =>
        kotlinObject(arena, index).movementY,
    uranium_NativeMouseEvent_getButton: (arena, index) =>
        kotlinObject(arena, index).button,
    uranium_NativeMouseEvent_getButtons: (arena, index) =>
        kotlinObject(arena, index).buttons,
    uranium_NativeMouseEvent_getAltKey: (arena, index) =>
        kotlinObject(arena, index).altKey,
    uranium_NativeMouseEvent_getCtrlKey: (arena, index) =>
        kotlinObject(arena, index).ctrlKey,
    uranium_NativeMouseEvent_getMetaKey: (arena, index) =>
        kotlinObject(arena, index).metaKey,
    uranium_NativeMouseEvent_getShiftKey: (arena, index) =>
        kotlinObject(arena, index).shiftKey,
});

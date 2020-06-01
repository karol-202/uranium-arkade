konan.libraries.push({
    uranium_NativeKeyboardEvent_getType: (arena, index, resultArena) => {
        const event = kotlinObject(arena, index);
        return newArenaObject(resultArena, event.type);
    },
    uranium_NativeKeyboardEvent_getKey: (arena, index, resultArena) => {
        const event = kotlinObject(arena, index);
        return newArenaObject(resultArena, event.key)
    },
    uranium_NativeKeyboardEvent_getCode: (arena, index, resultArena) => {
        const event = kotlinObject(arena, index);
        return newArenaObject(resultArena, event.code)
    },
    uranium_NativeKeyboardEvent_getRepeat: (arena, index) =>
        kotlinObject(arena, index).repeat,
    uranium_NativeKeyboardEvent_getAltKey: (arena, index) =>
        kotlinObject(arena, index).altKey,
    uranium_NativeKeyboardEvent_getCtrlKey: (arena, index) =>
        kotlinObject(arena, index).ctrlKey,
    uranium_NativeKeyboardEvent_getMetaKey: (arena, index) =>
        kotlinObject(arena, index).metaKey,
    uranium_NativeKeyboardEvent_getShiftKey: (arena, index) =>
        kotlinObject(arena, index).shiftKey,
});

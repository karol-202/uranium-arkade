konan.libraries.push({
    uranium_NativePath_Companion_create: (resultArena) =>
        newArenaObject(resultArena, Path2D()),
    uranium_NativePath_Companion_fromData: (dataPtr, dataLength, resultArena) => {
        const data = toUTF16String(dataPtr, dataLength);
        return newArenaObject(resultArena, Path2D(data))
    },
    uranium_NativePath_moveTo: (arena, index, x, y) =>
        kotlinObject(arena, index).moveTo(x, y),
    uranium_NativePath_lineTo: (arena, index, x, y) =>
        kotlinObject(arena, index).lineTo(x, y),
    uranium_NativePath_closePath: (arena, index) =>
        kotlinObject(arena, index).closePath()
});

konan.libraries.push({
    uranium_NativeFillStyle_Companion_fromString: (strPtr, strLength, resultArena) => {
        const string = toUTF16String(strPtr, strLength);
        return newArenaObject(resultArena, string);
    }
});

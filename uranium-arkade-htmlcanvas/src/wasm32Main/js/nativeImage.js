konan.libraries.push({
    uranium_NativeImage_Companion_load: (srcPtr, srcLength, resultArena) => {
        const image = new Image();
        image.src = toUTF16String(srcPtr, srcLength);
        return newArenaObject(resultArena, image);
    }
});

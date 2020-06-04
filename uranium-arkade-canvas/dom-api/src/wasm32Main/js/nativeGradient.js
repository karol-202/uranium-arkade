konan.libraries.push({
    uranium_NativeGradient_addColorStop: (arena, index, offset, colorPtr, colorLength) => {
        const color = toUTF16String(colorPtr, colorLength);
        kotlinObject(arena, index).addColorStop(offset, color);
    }
});

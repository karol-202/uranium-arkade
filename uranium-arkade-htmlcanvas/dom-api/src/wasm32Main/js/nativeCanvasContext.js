konan.libraries.push({
    uranium_NativeCanvasContext_getCanvas: (arena, index, resultArena) => {
        const context = kotlinObject(arena, index);
        return newArenaObject(resultArena, context.canvas);
    },
    uranium_NativeCanvasContext_getFillStyle: (arena, index, resultArena) => {
        const context = kotlinObject(arena, index);
        return newArenaObject(resultArena, context.fillStyle);
    },
    uranium_NativeCanvasContext_setFillStyle: (arena, index, styleArena, styleIndex) => {
        const context = kotlinObject(arena, index);
        context.fillStyle = kotlinObject(styleArena, styleIndex);
    },
    uranium_NativeCanvasContext_getFont: (arena, index, resultArena) => {
        const context = kotlinObject(arena, index);
        return newArenaObject(resultArena, context.font);
    },
    uranium_NativeCanvasContext_setFont: (arena, index, fontPtr, fontLength) => {
        const context = kotlinObject(arena, index);
        context.font = toUTF16String(fontPtr, fontLength);
    },
    uranium_NativeCanvasContext_getTextAlign: (arena, index, resultArena) => {
        const context = kotlinObject(arena, index);
        return newArenaObject(resultArena, context.textAlign);
    },
    uranium_NativeCanvasContext_setTextAlign: (arena, index, alignPtr, alignLength) => {
        const context = kotlinObject(arena, index);
        context.textAlign = toUTF16String(alignPtr, alignLength);
    },
    uranium_NativeCanvasContext_getTextBaseline: (arena, index, resultArena) => {
        const context = kotlinObject(arena, index);
        return newArenaObject(resultArena, context.textBaseline);
    },
    uranium_NativeCanvasContext_setTextBaseline: (arena, index, baselinePtr, baselineLength) => {
        const context = kotlinObject(arena, index);
        context.textBaseline = toUTF16String(baselinePtr, baselineLength);
    },
    uranium_NativeCanvasContext_getDirection: (arena, index, resultArena) => {
        const context = kotlinObject(arena, index);
        return newArenaObject(resultArena, context.direction);
    },
    uranium_NativeCanvasContext_setDirection: (arena, index, directionPtr, directionLength) => {
        const context = kotlinObject(arena, index);
        context.direction = toUTF16String(directionPtr, directionLength);
    },
    uranium_NativeCanvasContext_fillRect: (arena, index, x, y, width, height) =>
        kotlinObject(arena, index).fillRect(x, y, width, height),
    uranium_NativeCanvasContext_clearRect: (arena, index, x, y, width, height) =>
        kotlinObject(arena, index).clearRect(x, y, width, height),
    uranium_NativeCanvasContext_drawImage: (arena, index, imageArena, imageIndex, drawX, drawY) => {
        const image = kotlinObject(imageArena, imageIndex);
        kotlinObject(arena, index).drawImage(image, drawX, drawY)
    },
    uranium_NativeCanvasContext_drawImage_size: (arena, index, imageArena, imageIndex,
                                                 drawX, drawY, drawWidth, drawHeight) => {
        const image = kotlinObject(imageArena, imageIndex);
        kotlinObject(arena, index).drawImage(image, drawX, drawY, drawWidth, drawHeight)
    },
    uranium_NativeCanvasContext_drawImage_src_size: (arena, index, imageArena, imageIndex,
                                                     srcX, srcY, srcWidth, srcHeight,
                                                     drawX, drawY, drawWidth, drawHeight) => {
        const image = kotlinObject(imageArena, imageIndex);
        kotlinObject(arena, index).drawImage(image, srcX, srcY, srcWidth, srcHeight, drawX, drawY, drawWidth, drawHeight)
    },
    uranium_NativeCanvasContext_fillText: (arena, index, textPtr, textLength, x, y) => {
        const text = toUTF16String(textPtr, textLength);
        kotlinObject(arena, index).fillText(text, x, y);
    },
    uranium_NativeCanvasContext_fillText_max: (arena, index, textPtr, textLength, x, y, maxWidth) => {
        const text = toUTF16String(textPtr, textLength);
        kotlinObject(arena, index).fillText(text, x, y, maxWidth);
    },
    uranium_NativeCanvasContext_beginPath: (arena, index) =>
        kotlinObject(arena, index).beginPath(),
    uranium_NativeCanvasContext_moveTo: (arena, index, x, y) =>
        kotlinObject(arena, index).moveTo(x, y),
    uranium_NativeCanvasContext_lineTo: (arena, index, x, y) =>
        kotlinObject(arena, index).lineTo(x, y),
    uranium_NativeCanvasContext_arc: (arena, index, x, y, radius, startAngle, endAngle, anticlockwise) =>
        kotlinObject(arena, index).arc(x, y, radius, startAngle, endAngle, anticlockwise),
    uranium_NativeCanvasContext_closePath: (arena, index) =>
        kotlinObject(arena, index).closePath(),
    uranium_NativeCanvasContext_fill: (arena, index, fillRulePtr, fillRuleLength) => {
        const fillRule = toUTF16String(fillRulePtr, fillRuleLength);
        kotlinObject(arena, index).fill(fillRule);
    },
    uranium_NativeCanvasContext_fill_path: (arena, index, pathArena, pathIndex, fillRulePtr, fillRuleLength) => {
        const path = kotlinObject(pathArena, pathIndex);
        const fillRule = toUTF16String(fillRulePtr, fillRuleLength);
        kotlinObject(arena, index).fill(path, fillRule);
    },
    uranium_NativeCanvasContext_save: (arena, index) =>
        kotlinObject(arena, index).save(),
    uranium_NativeCanvasContext_restore: (arena, index) =>
        kotlinObject(arena, index).restore(),
    uranium_NativeCanvasContext_translate: (arena, index, x, y) =>
        kotlinObject(arena, index).translate(x, y),
    uranium_NativeCanvasContext_scale: (arena, index, x, y) =>
        kotlinObject(arena, index).scale(x, y),
    uranium_NativeCanvasContext_createLinearGradient: (arena, index, x0, y0, x1, y1, resultArena) => {
        const context = kotlinObject(arena, index);
        return newArenaObject(resultArena, context.createLinearGradient(x0, y0, x1, y1));
    },
    uranium_NativeCanvasContext_createRadialGradient: (arena, index, x0, y0, r0, x1, y1, r1, resultArena) => {
        const context = kotlinObject(arena, index);
        return newArenaObject(resultArena, context.createRadialGradient(x0, y0, r0, x1, y1, r1));
    },
});

konan.libraries.push({
    uranium_string_length: (arena, index) =>
        kotlinObject(arena, index).length,
    uranium_string_data: (arena, index, charIndex) =>
        kotlinObject(arena, index).charCodeAt(charIndex)
});

function newArenaObject(arena, index) {
    return konan_dependencies.env.Konan_js_addObjectToArena(arena, index)
}

function wrapLambda(lambdaIndex, arena) {
    return konan_dependencies.env.Konan_js_wrapLambda(lambdaIndex, arena);
}

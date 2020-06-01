konan.libraries.push({
    uranium_string_length: (arena, index) =>
        kotlinObject(arena, index).length,
    uranium_string_data: (arena, index, charIndex) =>
        kotlinObject(arena, index).charCodeAt(charIndex)
});

function newArenaObject(arenaIndex, object) {
    const arena = konan_dependencies.env.arenas.get(arenaIndex);
    arena.push(object);
    return arena.length - 1;
}

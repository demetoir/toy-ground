'use client'

import { ExampleShit, Shit } from "@/proto/x-state";

export function Button() {
    const onClick = async () => {
        // ExampleShit()
        Shit()

        return {}
    }

    return <button

        onClick={onClick}>run
    </button>

}

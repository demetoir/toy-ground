'use client';
import { useBoolean } from "@/hooks/useBoolean";

export function Toggle() {
    const tog = useBoolean()
    return <button
        onClick={() => {
            tog.update((x) => !x)
        }}
    >{tog.value?'true':'false'}
    </button>
}

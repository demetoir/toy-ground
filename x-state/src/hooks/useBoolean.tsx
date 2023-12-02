import { useState } from "react";

export function useBoolean() {
    const [value, update] = useState(false)

    return {
        value,
        update
    }
}

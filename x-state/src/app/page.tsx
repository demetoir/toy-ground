'use client'


import { Shit } from "@/proto/x-state";

export default function Home() {
    const onClick = () => {
        'use client'

        Shit()
    }

    return (
        <main className="flex p-24 gap-1">

            <button

                onClick={onClick}>run
            </button>
        </main>
    )
}



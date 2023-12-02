'use client'


import { asServerAction, NoneCommand } from "@/server/server-action";

export function Button() {
    const onClick = async () => {
        await asServerAction()
        await NoneCommand()
        console.log('shit')
    }


    return <button
        id={'shit'}

        onClick={onClick}>run
    </button>

}



'use client'


import { asServerAction, NoneCommand } from "@/server/server-action";

export function CallCommandButton(props: { id: string }) {
    const onClick = async () => {
        await asServerAction()
        await NoneCommand()
        console.log(`shit ${props.id}`)

    }


    return <button
        id={'shit'}

        onClick={onClick}>run
    </button>

}



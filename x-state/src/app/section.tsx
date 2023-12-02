import { Button } from "@/component/button";
import { Toggle } from "@/component/toggle";
import React from "react";
import { fuckQuery } from "@/server/server-action";

export async function Section() {
    const res = await fuckQuery()
    console.log('home')

    return <div className={'flex w-40 flex-row justify-between'}>

        {JSON.stringify(res)}
        <Button/>


        <Toggle/>


    </div>


}

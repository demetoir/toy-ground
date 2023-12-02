import { CallCommandButton } from "@/component/call-command-button";
import { Toggle } from "@/component/toggle";
import React from "react";
import { fuckQuery } from "@/server/server-action";
import { GoPostPageAsLinkButton } from "@/component/go-post-page-as-link-button";
import { GoPostPageButtonAsRouter } from "@/component/go-post-page-button-as-router-button";
import { PlainComponent } from "@/component/plain-component";
import { Form } from "@/component/form";

export async function Section() {
    const res = await fuckQuery()
    console.log('home')

    return <div className={'flex w-40 flex-row justify-between'}>

        {JSON.stringify(res)}
        <CallCommandButton id={res}/>

        <Toggle/>

        <GoPostPageAsLinkButton/>

        <GoPostPageButtonAsRouter/>

        <PlainComponent/>

        <Form/>

    </div>
}



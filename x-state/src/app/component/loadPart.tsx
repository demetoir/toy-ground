'use client'


import { CallCommandButton } from "@/component/call-command-button";
import { Toggle } from "@/component/toggle";

import React from "react";

export function LoadPart(props: { id: string }) {
    return <>
        <CallCommandButton id={props.id}/>
        <Toggle/>
        
    </>


}



// input name
// rhf
// signal
// react hook form
// register
//

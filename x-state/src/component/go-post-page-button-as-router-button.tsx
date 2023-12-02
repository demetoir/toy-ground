'use client'
import { useRouter } from "next/navigation";
import React, { useCallback } from "react";

export function GoPostPageButtonAsRouter() {
    const router = useRouter()
    const onButtonClick = useCallback((event: React.MouseEvent<HTMLButtonElement>) => {
        router.push('/post')
    }, []);

    return <button onClick={onButtonClick}>go post page as router</button>
}


// async function component => 'server component'
// function component => 'client component'

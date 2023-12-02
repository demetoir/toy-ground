import Link from "next/link";
import React from "react";

export async function GoPostPageAsLinkButton() {
    return <Link href={'/post'}>
        <button>go post page as link</button>
    </Link>
}

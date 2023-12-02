'use server'
import { sleep } from "@/util/sleep";

// import { serverAction } from "@/app/server-action";

export async function callServerAction<T>(fn: () => Promise<T>): Promise<T> {

    // await serverAction()

    const aaa = await fn()

    return aaa

}


export async function fuckCommand() {
    // await serverAction()
    await sleep(1000)
    console.log('command')

    return 'abd'
}


export async function fuckQuery() {
    await sleep(1000)
    console.log('query')
    return '123'
}

export async function NoneCommand() {
    console.log('none command')
}


export async function asServerAction() {
    console.log('asServerAction')
}


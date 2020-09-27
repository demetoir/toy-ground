// import {PrismaClient, User, UserCreateInput} from '../.prisma/client'
// import {PrismaClient, User, UserCreateInput} from "../prisma/client"
// todo generated 된 package.json의 name을 @wrap/prisma로 변경해야한다
//  그래야 webstorm에서 임포트시 정적분석이 된다
// 인줄 알았는데 해당 generated 된 폴더를 un exclude시키니 잘나온다
// https://intellij-support.jetbrains.com/hc/en-us/community/posts/360009385399-Go-to-declaration-auto-import-doesn-t-work-in-certain-situations
import {PrismaClient, User, UserCreateInput} from "@prisma/client"

(async function () {
    const prisma: PrismaClient = new PrismaClient()

    const userInput: UserCreateInput = {
        name: "12312",
        email: "123123"
    }

    const user: User = await prisma.user.create({data: userInput})

    console.log(user)
})()

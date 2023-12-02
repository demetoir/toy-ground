import './globals.css'


export default function RootLayout(props: {
    children: React.ReactNode
}) {
    return (
        <html lang="en">
        <body
            suppressHydrationWarning
        >
        <main>{props.children}</main>
        </body>
        </html>
    )
}

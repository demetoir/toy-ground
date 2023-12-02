import { FieldValues, useForm, useFormContext } from "react-hook-form";
import { typedPath, TypedPathWrapper } from "typed-path";

export class FormDefine<TFieldValues extends FieldValues = FieldValues, TContext = any, TTransformedValues extends FieldValues | undefined = undefined> {
    get path() {
        return typedPath<TFieldValues>()
    }

    get useForm() {
        return useForm<TFieldValues>
    }

    get useContext() {
        return useFormContext<TFieldValues>
    }

    toName(path: TypedPathWrapper<unknown, any>): any {
        return path.$rawPath.join('.')
    }

    static toName(path: TypedPathWrapper<unknown, any>): any {
        return path.$rawPath.join('.')
    }


    static get useForm() {
        return useForm
    }

    static get useContext() {
        return useFormContext
    }
}


export function FormInput(props: { path: TypedPathWrapper<unknown, any> }) {
    const form = FormDefine.useContext()

    return <input {...form.register(FormDefine.toName(props.path))} />
}

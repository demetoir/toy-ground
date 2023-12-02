'use client'
import { FormProvider, SubmitHandler, UseFormReturn } from "react-hook-form"
import { FormDefine, FormInput } from "@/component/form-define";
import React from "react";

enum GenderEnum {
    female = "female",
    male = "male",
    other = "other",
}

interface ExampleFormValueModel {
    firstName: string
    gender: GenderEnum
    shit: { ass: number }[]
}

const exampleFormDefine = new FormDefine<ExampleFormValueModel>()

export class ExampleFormValue {
    constructor(
        public readonly model: ExampleFormValueModel
    ) {}

    static fromForm(form: UseFormReturn<any, any, any>) {
        return new ExampleFormValue(form.getValues() as any)
    }


    dumpForm(form: UseFormReturn<any, any, any>) {
        const keys = Object.keys(this.model)

        for (const key of keys) {
            // @ts-ignore
            const value = this.model[key];

            form.setValue(key, value)
        }
    }


    updateShit() {

    }


    validate() {

    }


}


export function Form() {
    const form = exampleFormDefine.useForm()
    const {register, handleSubmit} = form;

    const handler = async () => {
        const formValue = ExampleFormValue.fromForm(form)

        const keys = Object.keys(formValue.model)

        for (const key of keys) {
            // @ts-ignore
            form.setValue(key, formValue.model[key])
        }


        // form.setValue('root', formValue.model)
        //
        // form.reset(formValue.model)

        // // todo trigger
        // form.clearErrors()
        // await form.trigger()
    }


    const onSubmit: SubmitHandler<ExampleFormValueModel> = (data) => console.log(data)

    const onButtonClick = () => {

    };

    const onClick = async () => {

    };

    return (
        <FormProvider {...form}>
            <form onSubmit={handleSubmit(onSubmit)}>

                <label>First Name</label>
                <input {...register(exampleFormDefine.toName(exampleFormDefine.path.shit[0].ass))} />

                <label>Gender Selection</label>
                <select {...register(exampleFormDefine.toName(exampleFormDefine.path.gender))}>
                    <option value="female">female</option>
                    <option value="male">male</option>
                    <option value="other">other</option>
                </select>

                <button type={'submit'}>submit</button>
                <button onClick={handler}>handle</button>
                <Inside/>
            </form>
        </FormProvider>

    )
}


function Inside() {
    return <div>
        <label>extra shit</label>
        <FormInput path={exampleFormDefine.path.shit[0].ass}/>
    </div>

}


function Button(props: {
    //
    name?: string, id?: string, className?: string, onClick?: () => Promise<void>
    children?: any
}) {
    return <button {...props}/>
}

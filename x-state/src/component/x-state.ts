import { createMachine, interpret } from "xstate";

export function ExampleShit() {
    const shitMachine = createMachine({
        id: 'shit',
        initial: 'init',
        schema: {
            // The context (extended state) of the machine
            context: {} as {
                elapsed: number
            },
            // The events this machine handles
            events: {} as
                | {
                type: 'freeze'
            }
                | {
                type: 'heat'
            }
                | {
                type: 'PED_COUNTDOWN';
                duration: number
            }
        },
        states: {
            init: {
                on: {
                    freeze: {target: 'frozen'},
                    heat: {target: 'melted'}
                }
            },
            frozen: {
                on: {
                    heat: {target: 'melted'}
                }
            },
            melted: {
                on: {
                    freeze: {target: 'frozen'}
                }
            }
        },

    });


    console.log('start')
    let state


    {
        const service = interpret(shitMachine).onTransition((state, event,) =>
            console.log({
                stateValue: state.value,
                eventType: event.type

            })
        );
        service.start();


        console.log({
            currentState: service.getSnapshot()
        })

        service.send({type: 'heat'});
        console.log({
            currentState: service.getSnapshot()
        })
        service.send({type: 'freeze'});
        console.log({
            currentState: service.getSnapshot()
        })

        console.log({
            persistedState: service.getSnapshot()
        })
        state = service.getSnapshot()

    }

    console.log('from load')

    {

        // https://xstate.js.org/docs/guides/states.html#persisting-state
        // * load state
        const service = interpret(shitMachine)
            //
            .onTransition((state, event) =>
                console.log({
                    stateValue: state.value,
                    eventType: event.type

                })
            )
        service.start(state)


        console.log({
            can: {
                type: 'freeze',
                res: service.state.can('freeze',)
            }
        })


        console.log({
            loadState: service.getSnapshot().value
        })


        const res = service.send({type: 'freeze'});
        console.log({
            currentState: service.getSnapshot(),
            hasChanged: res.changed
        })

        const res2 = service.send({type: 'heat'});
        console.log({
            currentState: service.getSnapshot(),
            hasChanged: res2.changed
        })
    }


    // => 'resolved'

    console.log('end')

}


export function Shit() {
    const machine = createShitMachine()


    const service = interpret(machine).onEvent((event,) => {
        console.log({
            eventType: event.type
        })
    }).onTransition((state, event) => {

        console.log({
            eventType: event.type,
            changed: state.changed,
            state: state.value
        })
    })


    service.start()

    service.send('freeze')
    service.send('heat')
    service.send('freeze')
    service.send('break', {})
    service.send('combine')
    service.send('combine')
    service.send('combine')
    service.send('break')
    service.send('heat')

}


export function createShitMachine() {

    // with parallel state machine
    // done state
    // done persist
    // done parallel
    // done type safe
    // todo action https://xstate.js.org/docs/guides/actions.html#api
    // todo context https://xstate.js.org/docs/guides/context.html#assign-action
    // todo guard https://xstate.js.org/docs/guides/guards.html
    // todo effect https://xstate.js.org/docs/guides/effects.html
    // https://stately.ai/registry/editor/b01e5b07-7dac-40a3-a99f-6281b9e35218?machineId=be059dca-5ddd-4c7b-82ae-bf181a6c2ec3&mode=Design

    const machine = createMachine(
        {
            id: "shit machine",
            states: {
                shit: {
                    initial: "shit",
                    states: {
                        shit: {
                            on: {
                                freeze: {
                                    target: "frozen",
                                },
                                heat: {
                                    target: "melt",
                                },
                            },
                        },
                        frozen: {
                            initial: "combined",
                            states: {
                                combined: {
                                    on: {
                                        break: {
                                            target: "devided",
                                        },
                                    },
                                },
                                devided: {
                                    on: {
                                        combine: {
                                            target: "combined",
                                        },
                                    },
                                },
                            },
                            on: {
                                heat: {
                                    target: "melt",
                                },
                            },
                        },
                        melt: {
                            on: {
                                freeze: {
                                    target: "frozen",
                                },
                            },
                        },
                    },
                },
                feel: {
                    initial: "good",
                    states: {
                        good: {
                            on: {
                                freeze: {
                                    target: "bad",
                                },
                            },
                        },
                        bad: {
                            on: {
                                heat: {
                                    target: "good",
                                },
                            },
                        },
                    },
                },
            },
            type: "parallel",
            schema: {
                events: {} as
                    | {
                    type: "freeze"
                }
                    | {
                    type: "heat"
                }
                    | {
                    type: "break"
                }
                    | {
                    type: "combine"
                },
            },
            predictableActionArguments: true,
            preserveActionOrder: true,
        },
        {
            actions: {},
            services: {},
            guards: {},
            delays: {},
        },
    );

    return machine
}

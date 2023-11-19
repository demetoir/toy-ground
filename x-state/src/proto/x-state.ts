
import { createMachine, interpret } from "xstate";

export function Shit() {
    const promiseMachine = createMachine({
        id: 'promise',
        initial: 'pending',
        states: {
            pending: {
                on: {
                    RESOLVE: {target: 'resolved'},
                    REJECT: {target: 'rejected'}
                }
            },
            resolved: {
                type: 'final'
            },
            rejected: {
                type: 'final'
            }
        }
    });

    const promiseService = interpret(promiseMachine).onTransition((state) =>
        console.log(state.value)
    );

    console.log('clicked')


    // Start the service
    promiseService.start();
    // => 'pending'

    promiseService.send({type: 'RESOLVE'});
    // => 'resolved'

}

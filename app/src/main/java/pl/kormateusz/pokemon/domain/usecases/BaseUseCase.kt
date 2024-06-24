package pl.kormateusz.pokemon.domain.usecases

abstract class BaseParamUseCase<T, E> {
    protected abstract suspend fun buildUseCase(param: T): E

    open suspend fun execute(param: T): E {
        return this.buildUseCase(param)
    }
}

abstract class BaseNoParamUseCase<E> {
    protected abstract suspend fun buildUseCase(): E

    open suspend fun execute(): E {
        return this.buildUseCase()
    }
}
import time
from gerador import Gerador

class Ordering:
    """
    Classe com metodos de ordenação do python e quicksort
    """
    def sort_python(names):
        """
        Ordena uma lista de nomes usando o método sort do Python com timer de tempo.

        Args:
            names (list): Lista de nomes a ser ordenada.
        """
        start_time = time.time()
        names.sort()
        Gerador.save_to_file(names, './names_sorted_python.txt')
        end_time = time.time()
        print(f"Metodo Sort do python Terminou com {end_time - start_time} seconds")

    def particiona(lista, ini, fim):
        """
        Particiona a lista para o algoritmo QuickSort.

        Args:
            lista (list): Lista a ser particionada.
            ini (int): Índice inicial da partição.
            fim (int): Índice final da partição.

        Returns:
            int: Índice do pivô após a partição.
        """
        pivo = lista[ini]
        i = ini + 1
        j = fim

        while True:
            while i <= j and lista[i] <= pivo:
                i += 1
            while i <= j and lista[j] >= pivo:
                j -= 1
            if i <= j:
                lista[i], lista[j] = lista[j], lista[i]
            else:
                break

        lista[ini], lista[j] = lista[j], lista[ini]
        return j

    def quick(lista, ini, fim):
        """
        Implementa o algoritmo QuickSort de forma recursiva.

        Args:
            lista (list): Lista a ser ordenada.
            ini (int): Índice inicial da partição.
            fim (int): Índice final da partição.
        """
        if ini < fim:
            pivo = Ordering.particiona(lista, ini, fim)
            Ordering.quick(lista, ini, pivo - 1)
            Ordering.quick(lista, pivo + 1, fim)

    def quick_sort(lista):
        """
        Chama o algoritmo QuickSort com timer de tempo.

        Args:
            lista (list): Lista de nomes a ser ordenada.
        """
        start_time = time.time()
        Ordering.quick(lista, 0, len(lista) - 1)
        Gerador.save_to_file(lista, './names_sorted_quick.txt')
        end_time = time.time()
        print(f"Metodo QuickSort terminou com {end_time - start_time} seconds")

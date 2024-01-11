class TestingGeneratePairsMethod extends munit.FunSuite {

  test("Checking if the method made for testing the method generatePairs (with the same logic) " +
    "is working corectlly "){
    val list = List(1,2,3,4)

    val pairs = Simulator.mftgpm(list)

    // Now perform your assertions on 'pairs'
    // For example, checking the size of the list
    assertEquals(pairs, List((1,2), (1,3), (1,4), (2,3), (2,4), (3,4)))
  }

  test("Checking if the method made for testing the method generatePairs (with the same logic) " +
    "is working corectlly for one element ") {
    val list = List(1)

    val pairs = Simulator.mftgpm(list)

    // Now perform your assertions on 'pairs'
    // For example, checking the size of the list
    assertEquals(pairs, List())
  }

}

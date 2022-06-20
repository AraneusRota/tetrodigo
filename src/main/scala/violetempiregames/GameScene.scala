package violetempiregames

import indigo.*
import indigo.scenes.*
import violetempiregames.init.Assets
import violetempiregames.model.Model
import violetempiregames.view.BlockView

object GameScene extends Scene[Unit, Model, Unit]:
  
  type SceneModel     = Model
  type SceneViewModel = Unit

  val name: SceneName =
    SceneName("game")

  val modelLens: Lens[Model, Model] =
    Lens.keepLatest

  val viewModelLens: Lens[Unit, Unit] =
    Lens.keepLatest

  val eventFilters: EventFilters =
    EventFilters.Permissive

  val subSystems: Set[SubSystem] =
    Set()

  def updateModel(
      context: FrameContext[Unit],
      model: Model
  ): GlobalEvent => Outcome[Model] =
    event => Outcome(model.update(context, event))

  def updateViewModel(
      context: FrameContext[Unit],
      model: Model,
      viewModel: Unit
  ): GlobalEvent => Outcome[Unit] =
    _ => Outcome(viewModel)

  def present(
      context: FrameContext[Unit],
      model: Model,
      viewModel: Unit
  ): Outcome[SceneUpdateFragment] =
    Outcome(
      SceneUpdateFragment(
        BlockView(model.blocks)
      )
    )

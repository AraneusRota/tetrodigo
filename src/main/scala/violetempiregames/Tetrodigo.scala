package violetempiregames

import indigo.*
import indigo.scenes.*
import violetempiregames.init.Assets
import violetempiregames.init.ViewConfig
import violetempiregames.model.Model

import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("IndigoGame")
object Tetrodigo extends IndigoGame[Unit, Unit, Model, Unit]:

  override def initialScene(bootData: Unit): Option[SceneName] =
    None

  override def scenes(bootData: Unit): NonEmptyList[Scene[Unit, Model, Unit]] =
    NonEmptyList(GameScene)

  override val eventFilters: EventFilters =
    EventFilters.Permissive

  override def boot(flags: Map[String, String]): Outcome[BootResult[Unit]] =
    Outcome(
      BootResult
        .noData(
          GameConfig.default
            .withViewport(ViewConfig.default.viewport)
            .withMagnification(ViewConfig.default.magnificationLevel)
        )
        .withAssets(Assets.assets)
    )

  override def initialModel(startupData: Unit): Outcome[Model] =
    Outcome(Model.initial)

  override def initialViewModel(startupData: Unit, model: Model): Outcome[Unit] =
    Outcome(())

  override def setup(
      bootData: Unit,
      assetCollection: AssetCollection,
      dice: Dice
  ): Outcome[Startup[Unit]] =
    Outcome(Startup.Success(()))

  override def updateModel(
      context: FrameContext[Unit],
      model: Model
  ): GlobalEvent => Outcome[Model] =
    _ => Outcome(model)

  override def updateViewModel(
      context: FrameContext[Unit],
      model: Model,
      viewModel: Unit
  ): GlobalEvent => Outcome[Unit] =
    _ => Outcome(viewModel)

  override def present(
      context: FrameContext[Unit],
      model: Model,
      viewModel: Unit
  ): Outcome[SceneUpdateFragment] =
    Outcome(SceneUpdateFragment.empty)
